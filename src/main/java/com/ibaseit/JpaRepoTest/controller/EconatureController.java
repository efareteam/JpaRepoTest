package com.ibaseit.JpaRepoTest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/eco")
public class EconatureController {

	private final static Logger LOGGER = LoggerFactory.getLogger(EconatureController.class);
	
	@GetMapping(value = "/hello")
	public Object hello() {
		LOGGER.info("inside hello method...");
		return "{\"name\":\"Welcome to EconatureController\"}";
	}

}
