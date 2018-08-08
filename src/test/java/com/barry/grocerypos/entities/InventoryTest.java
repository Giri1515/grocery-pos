package com.barry.grocerypos.entities;

import static org.junit.Assert.assertEquals;

import static org.hamcrest.MatcherAssert.assertThat;
import java.math.BigDecimal;

import org.hamcrest.Matchers;
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
		assertThat(new BigDecimal(10.00), Matchers.comparesEqualTo(retrievedItem.getPrice()));
		
	}
	
	@Test
	public void whenAdding2ItemsToInventoryCanRetrieveBothByName() {
		Inventory inventory = new Inventory();
		
		inventory.addItem("Bacon", 10.00);
		inventory.addItem("Cheese", 5.00);
		
		Item baconItem = inventory.getItemByName("Bacon");
		Item cheeseItem = inventory.getItemByName("Cheese");
		
		assertEquals("Bacon", baconItem.getName());
		assertThat(new BigDecimal(10.00), Matchers.comparesEqualTo(baconItem.getPrice()));
		assertEquals("Cheese", cheeseItem.getName());
		assertThat(new BigDecimal(5.00), Matchers.comparesEqualTo(cheeseItem.getPrice()));
		
		
	}


	
	

}
