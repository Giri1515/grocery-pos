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
	private int quantityRequired;
	
	@Getter
	@Setter
	private BigDecimal totalPrice;

	public BigDecimal getUnitPrice() {
		
		// scale to 3 to keep precision for correct calculations when dividing uneven values
		return totalPrice.divide(new BigDecimal(quantityRequired), 3, RoundingMode.HALF_UP); 
	}

}
