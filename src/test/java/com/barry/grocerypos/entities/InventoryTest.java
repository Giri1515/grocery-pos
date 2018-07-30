package com.barry.grocerypos.entities;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class InventoryTest {
	
	
	@Test
	public void inventoryCanRetrieveItemByName() {
		Inventory inventory = new Inventory();
		
		inventory.addItem("Bacon", 0.00);
		
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
	
	@Test
	public void whenAdding2ItemsToInventoryCanRetrieveBothByName() {
		Inventory inventory = new Inventory();
		
		inventory.addItem("Bacon", 10.00);
		inventory.addItem("Cheese", 5.00);
		
		Item baconItem = inventory.getItemByName("Bacon");
		Item cheeseItem = inventory.getItemByName("Cheese");
		
		assertEquals("Bacon", baconItem.getName());
		assertEquals(new BigDecimal(10.00), baconItem.getPrice());
		assertEquals("Cheese", cheeseItem.getName());
		assertEquals(new BigDecimal(5.00), cheeseItem.getPrice());
		
		
	}


	
	

}
