package com.barry.grocerypos.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PercentOffSpecialTest {
	
	@Test
	public void canHoldItemNameNumOfItemsAndPercentOff() {
		
		PercentOffSpecial percentOffSpecial = new PercentOffSpecial();
		
		percentOffSpecial.setItemName("Twizzlers");
		percentOffSpecial.setRequiredNumberOfItems(3);
		percentOffSpecial.setPercentOff(20);
		
		assertEquals("Twizzlers", percentOffSpecial.getItemName());
		assertEquals(3, percentOffSpecial.getRequiredNumberOfItems());
		assertEquals(20, percentOffSpecial.getPercentOff());
		
	}

	
}
