package com.barry.grocerypos.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public class Inventory {
	
	@Getter
	private Map<String, Item> itemMap = new HashMap<>();

	
	
	public Item getItemByName(String name) {
		return itemMap.get(name);
	}
	
	public void addItem(String name, double price) {
		
		Item newItem = new Item();
		newItem.setName(name);
		newItem.setPrice(new BigDecimal(price).setScale(2, RoundingMode.HALF_UP));
		
		itemMap.put(name, newItem);
		
		
	}

}
