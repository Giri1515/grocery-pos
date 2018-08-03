package com.barry.grocerypos.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barry.grocerypos.entities.Inventory;
import com.barry.grocerypos.entities.ScanRequest;

@RestController
@RequestMapping("/scanner")
public class ScannerController {

	@Autowired
	private Inventory inventory;
	
	@RequestMapping(value = "/items", method = POST, produces= "application/json")
	public String scanItem(@RequestBody ScanRequest scanRequest) {
		BigDecimal price = inventory.getItemByName(scanRequest.getItemName()).getPrice();
		
		return String.format("{\"orderTotal\":%s}", price.setScale(2, RoundingMode.HALF_UP));
	}
}
