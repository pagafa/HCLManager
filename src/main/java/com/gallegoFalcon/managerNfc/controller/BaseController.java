package com.gallegoFalcon.managerNfc.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class BaseController {

	@RequestMapping("/")
	public String index() {
		return "index.html";
	}

}
