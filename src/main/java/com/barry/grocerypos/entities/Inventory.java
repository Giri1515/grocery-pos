package com.barry.grocerypos.entities;

public class Inventory {
	
	
	public Item getItemByName(String name) {
		Item item = new Item();
		item.setName(name);
		return item;
	}

}
