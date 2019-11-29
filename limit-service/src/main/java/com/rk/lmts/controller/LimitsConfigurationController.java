package com.rk.lmts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rk.lmts.bean.LimitConfiguration;
import com.rk.lmts.configuration.Configuration;

@RestController
public class LimitsConfigurationController {

	@Autowired
	private Configuration config;
	
	@GetMapping("/limits")
	public LimitConfiguration reteriveLimitsFromConfiguration() {
		
		return new LimitConfiguration(config.getMaximum(),config.getMinimum());
	}
	
}
