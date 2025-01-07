package com.ecommerce.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.auth.JwtAuthRequest;
import com.ecommerce.auth.JwtAuthResponse;
import com.ecommerce.auth.JwtTokenHelper;
import com.ecommerce.module.user.service.UserOperations;
import com.ecommerce.module.user.value.UserRequest;
import com.ecommerce.module.user.value.UserResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/auth")
@Tag(name = "Authentication", description = "Endpoints for handling user authentication and authorization")
public class AuthController {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private JwtTokenHelper helper;

	@Autowired
	private UserOperations userOperations;

	@PostMapping("/login")
	@Operation(summary = "generate token")
	public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtAuthRequest request) {
		this.doAuthenticate(request.getEmail(), request.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		String token = this.helper.generateToken(userDetails);

		JwtAuthResponse response = JwtAuthResponse.builder()
				.token(token)
				.username(userDetails.getUsername())
				.build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private void doAuthenticate(String email, String password) {

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			manager.authenticate(authentication);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}
	}

	//	@ExceptionHandler(BadCredentialsException.class)
	//	public String exceptionHandler() {
	//		return "Credentials Invalid !!";
	//	}

	// @PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/register")
	@Operation(summary = "register new user")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
		UserResponse createdUser = this.userOperations.registerNewUser(request);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
}
