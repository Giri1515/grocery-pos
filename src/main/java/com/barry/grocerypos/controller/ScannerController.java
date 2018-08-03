package com.barry.grocerypos.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scanner")
public class ScannerController {

	@RequestMapping(value = "/items", method = POST, produces= "application/json")
	public String scanItem() {
		return "{\"orderTotal\":3.55}";
	}
}
