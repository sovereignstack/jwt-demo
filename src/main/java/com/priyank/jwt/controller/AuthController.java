package com.priyank.jwt.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priyank.jwt.service.TokenService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@RestController
public class AuthController {
	
	private final TokenService tokenService;

	@PostMapping("/token")
	public String token(Authentication authentication) {
		log.debug("Token requested for user '{}' ",authentication.getName());
		String token = tokenService.generateToken(authentication);
		log.debug("Token Granted {}",token);
		return token;
	}
	

}
