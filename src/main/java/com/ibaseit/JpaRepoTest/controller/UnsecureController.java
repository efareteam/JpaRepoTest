package com.ibaseit.JpaRepoTest.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibaseit.JpaRepoTest.service.AppService;


@RestController
@RequestMapping("/unsecure")
public class UnsecureController {

	static final Logger log = Logger.getLogger(UnsecureController.class);
	
	@Autowired
	AppService appService;

	@GetMapping(value = "/msg", produces = "application/json")
	public Object getMessage() {
		log.info("Start getMessage() in UnsecureController");
		appService.getMessage();
		log.info("End getMessage() in UnsecureController");
		return "Hello Unsecured Controller";
	}
}
