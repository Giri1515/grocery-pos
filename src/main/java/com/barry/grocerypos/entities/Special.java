package com.barry.grocerypos.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Getter;
import lombok.Setter;

public class Special {
	
	@Getter
	@Setter
	private String itemName;
	
	@Getter
	@Setter
	private int QuantityRequired;
	
	@Getter
	@Setter
	private BigDecimal totalPrice;

	public BigDecimal getUnitPrice() {
		
		return totalPrice.divide(new BigDecimal(QuantityRequired), 2, RoundingMode.HALF_UP); 
	}

}
