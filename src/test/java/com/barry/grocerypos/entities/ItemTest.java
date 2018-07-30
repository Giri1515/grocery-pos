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

}
