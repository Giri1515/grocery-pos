package com.barry.grocerypos.entities;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class Item {
	
	
	public Item() {
		weight = 1;
	}
	
	@Getter
	@Setter
	private BigDecimal price;
	
	
	@Getter
	@Setter
	private int weight;
	
	
	@Getter
	@Setter
	private String name;
	
	
	public BigDecimal totalPrice() {
		return price.multiply(new BigDecimal(weight));		
	}

}
