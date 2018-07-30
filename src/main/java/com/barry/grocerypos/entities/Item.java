package com.barry.grocerypos.entities;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class Item {
	
	@Getter
	@Setter
	private BigDecimal price;
	
	
	@Getter
	@Setter
	private int weight;

}
