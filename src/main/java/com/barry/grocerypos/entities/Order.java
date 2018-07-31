package com.barry.grocerypos.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {
	
	public List<Item> itemList = new ArrayList<>();
	
	private Map<String, Special> specialsMap = new HashMap<>();
	
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

	public void addSpecial(Special special) {
		specialsMap.put(special.getItemName(), special);
		
	}
	
	public Special getSpecialByName(String itemName) {
	
		return specialsMap.get(itemName);
		
	}

	public int getCountOfItem(String itemName) {

		return itemList.stream().filter(item-> item.getName().equals(itemName)).collect(Collectors.counting()).intValue();
	}
	
}
