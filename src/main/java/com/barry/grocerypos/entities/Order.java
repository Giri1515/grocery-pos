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
		if(itemList.size()>0)
			return itemList.get(0).getPrice();
		else
			return BigDecimal.ZERO;
	}
	
	
}
