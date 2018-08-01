package com.barry.grocerypos.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

public class PercentOffSpecial {
	
	@Getter
	@Setter
	private String itemName;
	
	@Getter
	@Setter
	private int percentOff;
	
	
	@Getter
	@Setter
	private int requiredNumberOfItems;


	public BigDecimal calcDiscountPrice(BigDecimal price) {
		
		BigDecimal percentPaying = new BigDecimal(100-percentOff).divide(new BigDecimal(100));
		
		return price.multiply(percentPaying);
	}
	
	public boolean qualifiesForPercentOffSpecial(List<Item> itemList) {
		
		// find items that apply to special
		List<Item> specialItems = itemList.stream().filter(item-> item.getName().equals(this.itemName))
				.collect(Collectors.toList());
		
		return specialItems.size() >= this.requiredNumberOfItems;
	}

}
