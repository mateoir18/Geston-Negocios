package com.negocios.controller;

import com.negocios.dto.AuthResponseDTO;
import com.negocios.dto.InitNegocioRequestDTO;
import com.negocios.dto.LoginRequestDTO;
import com.negocios.dto.RegisterRequestDTO;
import com.negocios.repository.NegocioRepository;
import com.negocios.repository.UsuarioRepository;
import com.negocios.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private NegocioRepository negocioRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
		try {
			AuthResponseDTO response = authService.login(loginRequest);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas: " + e.getMessage());
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerRequest) {
		try {
			AuthResponseDTO response = authService.register(registerRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en el registro: " + e.getMessage());
		}
	}
	
	// Registro de nuevo negocio (cualquiera puede registrar su negocio)
	@PostMapping("/register-negocio")
	public ResponseEntity<?> registerNegocio(@Valid @RequestBody InitNegocioRequestDTO initRequest) {
	    try {
	        // Verificar que el email no esté usado
	        if (usuarioRepository.existsByEmail(initRequest.getEmail())) {
	            return ResponseEntity.status(HttpStatus.CONFLICT)
	                    .body("El email ya está registrado");
	        }

	        // Verificar que el CIF/NIF no esté usado
	        if (negocioRepository.existsByCifNif(initRequest.getCifNif())) {
	            return ResponseEntity.status(HttpStatus.CONFLICT)
	                    .body("El CIF/NIF ya está registrado");
	        }

	        AuthResponseDTO response = authService.registerNegocio(initRequest);
	        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Error en el registro: " + e.getMessage());
	    }
	}
}