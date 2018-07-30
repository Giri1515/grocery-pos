package com.barry.grocerypos.entities;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class OrderTest {
	
	@Test
	
	public void addingItemIncreasesSizeByOne() throws Exception{
		
		Order order = new Order();
		
		Item item = new Item();
		
		order.addItem(item);
		
		assertEquals(1, order.getSize());
		
	}
	
	@Test
	public void addingTwoItemsIncreasesSizeByTwo() {
		Order order = new Order();
		
		order.addItem(new Item());
		order.addItem(new Item());
		
		assertEquals(2, order.getSize());
	
		
	}
	
	@Test
	public void orderTotalIsZeroWithNoItems() {
		
		Order order = new Order();
		
		assertEquals(new BigDecimal(0.00), order.total());
		
	}
	

}
