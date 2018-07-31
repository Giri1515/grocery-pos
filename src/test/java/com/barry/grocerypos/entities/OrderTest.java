package com.barry.grocerypos.entities;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {

	private Order order;
	
	@Before
	public void initializeOrder() {
		order = new Order();
	}
	
	
	@Test
	
	public void addingItemIncreasesSizeByOne() throws Exception{
		
		Item item = new Item();
		
		order.addItem(item);
		
		assertEquals(1, order.getSize());
		
	}
	
	@Test
	public void addingTwoItemsIncreasesSizeByTwo() {
		
		order.addItem(new Item());
		order.addItem(new Item());
		
		assertEquals(2, order.getSize());
	
		
	}
	
	@Test
	public void orderTotalIsZeroWithNoItems() {
		
		
		assertThat(BigDecimal.ZERO, Matchers.comparesEqualTo(order.total()));
		
	}
	
	@Test
	public void orderTotalIsSameAsPriceWhenOnlyOneItemAdded() {
		
		Item item = new Item();
		item.setPrice(new BigDecimal(10.00));
		
		order.addItem(item);
		
		assertThat(new BigDecimal(10.00), Matchers.comparesEqualTo(order.total()));
		
	}
	
	
	@Test
	public void orderTotalIsSumOfPricesWhenTwoItemsInOrder() {
		
		Item item1 = new Item();
		item1.setPrice(new BigDecimal(5.00));
		
		Item item2 = new Item();
		item2.setPrice(new BigDecimal(2.00));
		
		order.addItem(item1);
		order.addItem(item2);

		assertThat(new BigDecimal(7.00), Matchers.comparesEqualTo(order.total()));
	}
	
	
	@Test
	public void orderTotalIncludesPriceTimesWeight() {
		
		Item item1 = new Item();
		item1.setPrice(new BigDecimal(5.00));
		item1.setWeight(2);
		
		order.addItem(item1);
		
		assertThat(new BigDecimal(10.00), Matchers.comparesEqualTo(order.total()));
		
		
	}
	
	
	@Test 
	public void orderSizeDecreasesByOneWhenRemovingItem() {
		Item item = new Item();
		item.setName("Bacon");
		order.addItem(item);
		
		order.removeItem("Bacon");
		
		assertEquals(0, order.getSize());
		
	}
	
	@Test 
	public void orderSizeTotalReducesByPriceOfItemRemoved() {
		
		Item item1 = new Item();
		
		item1.setName("Bacon");
		item1.setPrice(new BigDecimal(5.75));
		order.addItem(item1);
		
		
		Item item2 = new Item();
		
		item2.setName("Eggs");
		item2.setPrice(new BigDecimal(3.99));
		order.addItem(item2);
		
		
		order.removeItem("Eggs");
		
		assertEquals(new BigDecimal(5.75), order.total());
		
	}
	
	
	@Test 
	public void whenAddSpecialToOrderCanGetSpecialByName() {
		
		Special special = new Special();
		
		special.setItemName("Bacon");
		special.setQuantityRequired(3);
		special.setTotalPrice(new BigDecimal(5.00));
		order.addSpecial(special);
		
		
		assertEquals("Bacon", order.getSpecialByName("Bacon").getItemName());
		
	}
	
	
	@Test 
	public void whenAdding1ItemToOrderCountOfItemReturns1() {
		
		Item item = new Item();
		item.setName("Bacon");
		item.setPrice(new BigDecimal(2.50));
		
		order.addItem(item);
		assertEquals(1, order.getCountOfItem("Bacon"));
		
	}
	
	@Test 
	public void whenAdding3OfSameItemToOrderCountByItemReturns3() {
		
		Item item = new Item();
		item.setName("Bacon");
		item.setPrice(new BigDecimal(2.50));
		
		order.addItem(item);
		order.addItem(item);
		order.addItem(item);
		
		assertEquals(3, order.getCountOfItem("Bacon"));
		
	}
	
	@Test 
	public void whenSpecialInFormOf3For5ExistsAdding3SpecialItemsCausesTotalToBe5() {
		
		Special special = new Special();
		
		special.setItemName("Bacon");
		special.setQuantityRequired(3);
		special.setTotalPrice(new BigDecimal(5.00));
		order.addSpecial(special);
		
		Item item = new Item();
		item.setName("Bacon");
		item.setPrice(new BigDecimal(2.50));
		
		order.addItem(item);
		order.addItem(item);
		order.addItem(item);
		
		
		assertThat(new BigDecimal(5.00), Matchers.comparesEqualTo(order.total()));
		
	}
	
	@Test 
	public void whenSpecialInFormOf3For5ExistsAdding4SpecialItemsCausesTotalToBe5PlusFullPriceOfOne() {
		
		Special special = new Special();
		
		special.setItemName("Bacon");
		special.setQuantityRequired(3);
		special.setTotalPrice(new BigDecimal(5.00));
		order.addSpecial(special);
		
		Item item1 = new Item();
		item1.setName("Bacon");
		item1.setPrice(new BigDecimal(2.50));
		order.addItem(item1);
		
		Item item2 = new Item();
		item2.setName("Bacon");
		item2.setPrice(new BigDecimal(2.50));
		order.addItem(item2);

		Item item3 = new Item();
		item3.setName("Bacon");
		item3.setPrice(new BigDecimal(2.50));
		order.addItem(item3);
		
		Item item4 = new Item();
		item4.setName("Bacon");
		item4.setPrice(new BigDecimal(2.50));

		order.addItem(item4);
		
		
		assertThat(new BigDecimal(7.50), Matchers.comparesEqualTo(order.total()));
		
	}

	
}
