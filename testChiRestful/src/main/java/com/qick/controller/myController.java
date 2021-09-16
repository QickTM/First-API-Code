package com.qick.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class myController {
	
	@PostMapping(value = "/api")
	public String hello() {
		return "Hello World, this is Spring Boot";
	}
}
