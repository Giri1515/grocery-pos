package com.barry.grocerypos.entities;

import lombok.Getter;
import lombok.Setter;

public class ScanRequest {

	@Getter
	@Setter
	private String itemName;
	
	
	@Getter
	@Setter
	private int weight;
	
}
