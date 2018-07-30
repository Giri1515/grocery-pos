package com.barry.grocerypos.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InventoryTest {
	
	
	@Test
	public void inventoryCanRetrieveItemByName() {
		Inventory inventory = new Inventory();
		
		Item retrievedItem = inventory.getItemByName("Bacon");
		
		assertEquals("Bacon", retrievedItem.getName());
		
		
	}
	
	

}
