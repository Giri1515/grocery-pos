package com.barry.grocerypos.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.barry.grocerypos.entities.Inventory;

public class ScannerTest {
	
	
	
	@Test
	public void scanningItemIncreasesOrderSize() {
		Inventory inventory = new Inventory();
		inventory.addItem("Bacon", 3.25);
		Scanner scanner = new Scanner();
		
		scanner.setInventory(inventory);
		
		scanner.scanItem("Bacon");
		
		assertEquals(1, scanner.getOrder().getSize());
		
	}
	
	
	@Test
	public void scanningItemIncreasesOrderTotalByItemPrice() {
		
		Inventory inventory = new Inventory();
		
		inventory.addItem("Bacon", 3.25);
		
		Scanner scanner = new Scanner();
		scanner.setInventory(inventory);
		
		scanner.scanItem("Bacon");
		
		assertEquals(new BigDecimal(3.25), scanner.getOrder().total());
		
	}
	
	@Test
	public void scanningItemWithNameAndWeightIncreasesOrderByItemPriceTimesWeight() {
		
		Inventory inventory = new Inventory();
		
		inventory.addItem("Bacon", 3.00);
		
		Scanner scanner = new Scanner();
		scanner.setInventory(inventory);
		
		scanner.scanItemWithWeight("Bacon", 2);
		
		assertThat(new BigDecimal(6.00), Matchers.comparesEqualTo(scanner.getOrder().total()));
		
		
		
	}
	
	
}
