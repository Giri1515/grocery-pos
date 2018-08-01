package com.barry.grocerypos.entities;

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

}
