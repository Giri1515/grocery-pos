package com.barry.grocerypos.entities;

import java.math.BigDecimal;

public class Inventory {
	
	
	
	public Item getItemByName(String name) {
		Item item = new Item();
		item.setName(name);
		item.setPrice(new BigDecimal(10.00));
		return item;
	}
	
	public void addItem(String name, double price) {
		
		
		
	}

}
