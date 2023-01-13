package com.priyank.jwt.controller;

import java.security.Principal;

import javax.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	@RolesAllowed("read")
	public String home(Principal principal) {
		return "Hello, "+principal.getName() + " - " + principal;
	}
}
