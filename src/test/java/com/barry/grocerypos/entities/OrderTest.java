package com.barry.grocerypos.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OrderTest {
	
	@Test
	
	public void addingItemIncreasesSizeByOne() throws Exception{
		
		Order order = new Order();
		
		Item item = new Item();
		
		order.addItem(item);
		
		assertEquals(1, order.getSize());
		
	}

}
