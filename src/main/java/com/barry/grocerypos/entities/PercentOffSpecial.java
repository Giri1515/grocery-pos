package com.barry.grocerypos.entities;

import java.math.BigDecimal;

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

}
