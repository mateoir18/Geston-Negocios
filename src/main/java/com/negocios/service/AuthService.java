package com.negocios.service;

import com.negocios.dto.AuthResponseDTO;
import com.negocios.dto.InitNegocioRequestDTO;
import com.negocios.dto.LoginRequestDTO;
import com.negocios.dto.RegisterRequestDTO;
import com.negocios.dto.UsuarioDTO;
import com.negocios.config.JwtUtil;
import com.negocios.mapper.UsuarioMapper;
import com.negocios.model.Negocio;
import com.negocios.model.Rol;
import com.negocios.model.Usuario;
import com.negocios.repository.NegocioRepository;
import com.negocios.repository.RolRepository;
import com.negocios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private NegocioRepository negocioRepository;

	@Autowired
	private RolRepository rolRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private UsuarioMapper usuarioMapper;

	public AuthResponseDTO login(LoginRequestDTO loginRequest) {
		// Autenticar usuario
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		// Cargar detalles del usuario
		UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

		// Generar token
		String token = jwtUtil.generateToken(userDetails);

		// Obtener usuario completo
		Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail())
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		UsuarioDTO usuarioDTO = usuarioMapper.toDTO(usuario);

		return new AuthResponseDTO(token, usuarioDTO);
	}

	public AuthResponseDTO register(RegisterRequestDTO registerRequest) {
		// Verificar si el email ya existe
		if (usuarioRepository.existsByEmail(registerRequest.getEmail())) {
			throw new RuntimeException("El email ya está registrado");
		}

		// Verificar que el negocio existe
		Negocio negocio = negocioRepository.findById(registerRequest.getNegocioId())
				.orElseThrow(() -> new RuntimeException("Negocio no encontrado"));

		// Verificar que el rol existe
		Rol rol = rolRepository.findById(registerRequest.getRolId())
				.orElseThrow(() -> new RuntimeException("Rol no encontrado"));

		// Crear nuevo usuario
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setEmail(registerRequest.getEmail());
		nuevoUsuario.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword())); // Encriptar contraseña
		nuevoUsuario.setNombreCompleto(registerRequest.getNombreCompleto());
		nuevoUsuario.setNegocio(negocio);
		nuevoUsuario.setRol(rol);

		// Guardar usuario
		Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

		// Generar token
		UserDetails userDetails = userDetailsService.loadUserByUsername(usuarioGuardado.getEmail());
		String token = jwtUtil.generateToken(userDetails);

		UsuarioDTO usuarioDTO = usuarioMapper.toDTO(usuarioGuardado);

		return new AuthResponseDTO(token, usuarioDTO);
	}

	public AuthResponseDTO registerNegocio(InitNegocioRequestDTO initRequest) {
		// Verificar que el email no esté usado (doble verificación)
		if (usuarioRepository.existsByEmail(initRequest.getEmail())) {
			throw new RuntimeException("El email ya está registrado");
		}

		// Verificar que el CIF/NIF no esté usado
		if (negocioRepository.existsByCifNif(initRequest.getCifNif())) {
			throw new RuntimeException("El CIF/NIF ya está registrado");
		}

		// Crear el negocio
		Negocio nuevoNegocio = new Negocio();
		nuevoNegocio.setNombre(initRequest.getNombreNegocio());
		nuevoNegocio.setCifNif(initRequest.getCifNif());
		nuevoNegocio.setDireccion(initRequest.getDireccion());
		Negocio negocioGuardado = negocioRepository.save(nuevoNegocio);

		// Obtener o crear el rol ADMIN
		Rol rolAdmin = rolRepository.findByNombreRol(Rol.NombreRol.ADMIN).orElseGet(() -> {
			Rol nuevoRol = new Rol(Rol.NombreRol.ADMIN);
			return rolRepository.save(nuevoRol);
		});

		// Obtener o crear el rol EMPLEADO (para tenerlo disponible)
		rolRepository.findByNombreRol(Rol.NombreRol.EMPLEADO).orElseGet(() -> {
			Rol nuevoRol = new Rol(Rol.NombreRol.EMPLEADO);
			return rolRepository.save(nuevoRol);
		});

		// Crear el usuario ADMIN
		Usuario nuevoAdmin = new Usuario();
		nuevoAdmin.setEmail(initRequest.getEmail());
		nuevoAdmin.setPasswordHash(passwordEncoder.encode(initRequest.getPassword()));
		nuevoAdmin.setNombreCompleto(initRequest.getNombreCompleto());
		nuevoAdmin.setNegocio(negocioGuardado);
		nuevoAdmin.setRol(rolAdmin);
		Usuario adminGuardado = usuarioRepository.save(nuevoAdmin);

		// Generar token
		UserDetails userDetails = userDetailsService.loadUserByUsername(adminGuardado.getEmail());
		String token = jwtUtil.generateToken(userDetails);

		UsuarioDTO usuarioDTO = usuarioMapper.toDTO(adminGuardado);

		return new AuthResponseDTO(token, usuarioDTO);
	}
}