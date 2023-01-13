package com.priyank.jwt.service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class TokenService {
	
	private final JwtEncoder encoder;
	
	public String generateToken(Authentication authentication) {
		Instant now = Instant.now();
		String scope = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(" "));
		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer("self")
				.issuedAt(now)
				.expiresAt(now.plus(1,ChronoUnit.SECONDS))
				.subject(authentication.getName())
				.claim("scope", scope)
				.build();
		log.debug("Issued At {}", now.atZone(ZoneOffset.UTC).toOffsetDateTime().toString());
		return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}
	
}
