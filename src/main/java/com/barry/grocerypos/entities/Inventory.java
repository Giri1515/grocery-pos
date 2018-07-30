package com.barry.grocerypos.entities;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
	
	
	private Map<String, Item> itemMap = new HashMap<>();

	
	
	public Item getItemByName(String name) {
		return itemMap.get(name);
	}
	
	public void addItem(String name, double price) {
		
		Item newItem = new Item();
		newItem.setName(name);
		newItem.setPrice(new BigDecimal(price));
		
		itemMap.put(name, newItem);
		
		
	}

}
