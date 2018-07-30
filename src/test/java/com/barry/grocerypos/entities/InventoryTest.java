package com.barry.grocerypos.entities;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class InventoryTest {
	
	
	@Test
	public void inventoryCanRetrieveItemByName() {
		Inventory inventory = new Inventory();
		
		Item retrievedItem = inventory.getItemByName("Bacon");
		
		assertEquals("Bacon", retrievedItem.getName());
		
		
	}
	

	@Test
	public void itemCanBeAddedToInventoryWithNameAndPrice() {
		Inventory inventory = new Inventory();
		
		inventory.addItem("Bacon", 10.00);
		
		Item retrievedItem = inventory.getItemByName("Bacon");
		
		assertEquals("Bacon", retrievedItem.getName());
		assertEquals(new BigDecimal(10.00), retrievedItem.getPrice());
		
		
	}

	
	

}
