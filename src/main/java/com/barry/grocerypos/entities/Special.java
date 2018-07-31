package com.barry.grocerypos.entities;

import java.math.BigDecimal;

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

}
