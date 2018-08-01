package com.barry.grocerypos.entities;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class SpecialTest {
	
	
	
	@Test 
	public void specialCanBeSetWithNameQuantityAndPriceToSupportNumForXPrice() {
		
		Special special = new Special();
		
		special.setItemName("Bacon");
		special.setQuantityRequired(3);
		special.setTotalPrice(new BigDecimal(5.00));
		
		assertEquals("Bacon", special.getItemName());
		assertEquals(3, special.getQuantityRequired());
		assertEquals(new BigDecimal(5.00), special.getTotalPrice());
	}
	

}
