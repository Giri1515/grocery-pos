package com.barry.grocerypos.service;

import com.barry.grocerypos.entities.Inventory;
import com.barry.grocerypos.entities.Item;
import com.barry.grocerypos.entities.Order;

import lombok.Getter;
import lombok.Setter;

public class Scanner {
	
	@Getter
	@Setter
	private Order order = new Order();
	
	@Setter
	private Inventory inventory;
	
	public void scanItem(String itemName) {
		Item item = inventory.getItemByName(itemName);
		
		order.addItem(item);
	}

	public void scanItemWithWeight(String itemName, int weight) {
		
		Item item = inventory.getItemByName(itemName);
		item.setWeight(weight);
		
		order.addItem(item);
		
	}

}
