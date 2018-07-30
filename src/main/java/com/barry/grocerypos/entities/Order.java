package com.barry.grocerypos.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
		
		itemList.remove(0);
		
	}
}
