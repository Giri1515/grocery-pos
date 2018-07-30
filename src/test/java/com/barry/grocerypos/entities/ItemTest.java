package com.barry.grocerypos.entities;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class ItemTest {
	
	@Test
	public void itemHasAPrice() {
		
		Item item = new Item();
		BigDecimal price = new BigDecimal(10.00);
		item.setPrice(price);
		
		assertEquals(price, item.getPrice());
	}
	
	@Test
	public void itemHasAWeight() {
		Item item = new Item();
		item.setWeight(2);
		
		
		assertEquals(2, item.getWeight());
		
	}

	
	@Test
	public void itemTotalPriceIsWeightTimesPrice() {
		Item item = new Item();
		item.setWeight(2);
		item.setPrice(new BigDecimal(5.00));
		
		assertEquals(new BigDecimal(10.00), item.totalPrice());
		
	}
	
}
