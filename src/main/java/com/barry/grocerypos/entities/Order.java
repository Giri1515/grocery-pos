package com.barry.grocerypos.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;

public class Order {
	
	@Getter
	public List<Item> itemList = new ArrayList<>();
	
	private Map<String, Special> specialsMap = new HashMap<>();
	private Map<String, MarkDown> markDownMap = new HashMap<>();
	
	
	public void addItem(Item newItem) {
		
		itemList.add(newItem);
	}
	
	public int getSize() {
		return itemList.size();
	}
	
	public BigDecimal total() {
		
		applySpecials();
		
		for (Item item : itemList) {
			MarkDown markDown = getMarkDownByName(item.getName());
			if(markDown!=null) {
				item.setPrice(item.getPrice().subtract(markDown.getPriceReduction()));
			}
		}
		
		
		BigDecimal total = itemList.stream()
				.map(Item::totalPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		return total.setScale(2, RoundingMode.HALF_UP);
	}

	public void applySpecials() {
		
		// if have item with special,then update prices with unit prices
		for (String itemName: specialsMap.keySet()) {
			if(itemList.stream().anyMatch(item->item.getName().equals(itemName))) {
				//get special
				Special special = specialsMap.get(itemName);
				BigDecimal unitPrice = special.getTotalPrice().divide(new BigDecimal(special.getQuantityRequired()), 3, RoundingMode.HALF_UP);
				if(getCountOfItem(itemName)>=special.getQuantityRequired()) {
					// set price to special price for number of items
					itemList.stream().filter(item->item.getName().equals(itemName))
						.limit(special.getQuantityRequired())
						.forEach(specialItem -> specialItem.setPrice(unitPrice));
				}
			}
		}
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

	public void addMarkDown(MarkDown markDown) {
		markDownMap.put(markDown.getItemName(), markDown);
		
	}

	public MarkDown getMarkDownByName(String itemName) {
		
		return markDownMap.get(itemName);
	}
	
	
	

	
}
