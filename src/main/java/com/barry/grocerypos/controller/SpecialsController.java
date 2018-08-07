package com.barry.grocerypos.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barry.grocerypos.entities.MarkDown;
import com.barry.grocerypos.entities.Order;

@RestController
@RequestMapping("/specials")
public class SpecialsController {
	
	@Autowired
	private Order order;
	
	@RequestMapping(value = "/markdowns", method=POST,  produces= "application/json")
	public String addMarkDown(@RequestBody MarkDown markDown) {
		
		order.addMarkDown(markDown);
		
		return "{\"message\":\"MarkDown Successfully Added\"}";
	}
	
	
	@RequestMapping(value = "/buyXForPrice", method=POST, produces= "application/json")
	public String addBuyXForPriceSpecial() {
		
		return "{\"message\":\"Special Successfully Added\"}";
	}

}
