package com.barry.grocerypos.entities;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class MarkDown {
	
	@Getter
	@Setter
	private String itemName;
	
	@Getter
	@Setter
	private BigDecimal priceReduction;

}
