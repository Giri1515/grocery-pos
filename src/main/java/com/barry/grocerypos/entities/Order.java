package com.barry.grocerypos.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Order {
	
	public List<Item> itemList = new ArrayList<>();
	
	public void addItem(Item newItem) {
		
		itemList.add(newItem);
	}
	
	public int getSize() {
		return itemList.size();
	}
	
	public BigDecimal total() {
		
		BigDecimal total = itemList.stream()
			.map(Item::totalPrice)
			.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		return total;
	}
	
	public void removeItem(String itemName) {
		
		this.itemList = itemList.stream().filter(p-> p.getName().equalsIgnoreCase(itemName)==false).collect(Collectors.toList());
		
	}
}
