package com.barry.grocerypos.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barry.grocerypos.entities.Inventory;
import com.barry.grocerypos.entities.Item;
import com.barry.grocerypos.entities.Order;
import com.barry.grocerypos.entities.ScanRequest;

@RestController
@RequestMapping("/scanner")
public class ScannerController {

	@Autowired
	private Inventory inventory;
	
	@Autowired
	private Order order;
	
	@RequestMapping(value = "/items", method = POST, produces= "application/json")
	public String scanItem(@RequestBody ScanRequest scanRequest) {
		Item item = inventory.getItemByName(scanRequest.getItemName());
		
		if(scanRequest.getWeight()!=0) {
			item.setWeight(scanRequest.getWeight());
		}
		order.addItem(item);
		
		
		return String.format("{\"orderTotal\":%s}", order.total().toString());
	}
}
