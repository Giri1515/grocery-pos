package com.barry.grocerypos.service;

import com.barry.grocerypos.entities.Item;
import com.barry.grocerypos.entities.Order;

import lombok.Getter;
import lombok.Setter;

public class Scanner {
	
	@Getter
	@Setter
	private Order order = new Order();
	
	public void scanItem(String itemName) {
		order.addItem(new Item());
	}

}
