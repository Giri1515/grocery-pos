package com.barry.grocerypos.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	
	@RequestMapping(value = "/items", method = POST, produces= "application/json")

	public String addItems() {
		return "";
	}
	
}
