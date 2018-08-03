package com.barry.grocerypos.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barry.grocerypos.entities.Item;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	
	@RequestMapping(value = "/items", method = POST, produces= "application/json")
	public Item addItems(@RequestBody Item item) {
		return item;
	}
	
}
