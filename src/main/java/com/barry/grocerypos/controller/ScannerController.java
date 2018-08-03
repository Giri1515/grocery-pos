package com.barry.grocerypos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scanner")
public class ScannerController {

	@RequestMapping("/items")
	public void scanItem() {
		
	}
}
