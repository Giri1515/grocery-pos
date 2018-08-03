package com.barry.grocerypos.entities;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hamcrest.Matchers;
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
		
		order.addItem(new Item("Taffy", 10.00));
		
		assertThat(new BigDecimal(10.00), Matchers.comparesEqualTo(order.total()));
		
	}
	
	
	@Test
	public void orderTotalIsSumOfPricesWhenTwoItemsInOrder() {
		
			
		order.addItem(new Item("Taffy", 5.00));
		order.addItem(new Item("Snickers", 2.00));

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
		

		order.addItem(new Item("Bacon", 5.75));
		
		order.addItem(new Item("Eggs", 3.99));
		
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
		
		
		
		order.addItem(new Item("Bacon", 2.50));
		assertEquals(1, order.getCountOfItem("Bacon"));
		
	}
	
	@Test 
	public void whenAdding3OfSameItemToOrderCountByItemReturns3() {
		
		Item item = new Item("Bacon", 2.50);
		
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
		
		Item item = new Item("Bacon", 2.50);
		
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
		
		
		order.addItem(new Item("Bacon", 2.50));
		
		order.addItem(new Item("Bacon", 2.50));

		order.addItem(new Item("Bacon", 2.50));
		
		order.addItem(new Item("Bacon", 2.50));
		
		
		assertThat(new BigDecimal(7.50), Matchers.comparesEqualTo(order.total()));
		
	}
	
	@Test 
	public void whenSpecialForBuy3For5DollarsExistAnd4ItemsAddedToOrderOneRemainsNormalPrice() {
		
		Special special = new Special();
		
		special.setItemName("Bacon");
		special.setQuantityRequired(3);
		special.setTotalPrice(new BigDecimal(5.00));
		order.addSpecial(special);
		
		
		order.addItem(new Item("Bacon", 2.50));
		
		order.addItem(new Item("Bacon", 2.50));

		order.addItem(new Item("Bacon", 2.50));
		
		order.addItem(new Item("Bacon", 2.50));
		
		
		order.applySpecials();
		
		List<Item> normalPricedList = order.getItemList().stream().filter(item->item.getPrice().equals(new BigDecimal(2.50))).collect(Collectors.toList());
		
		assertEquals(1, normalPricedList.size());
		assertEquals("Bacon", normalPricedList.get(0).getName());
		
	}
	
	
	@Test 
	public void whenAddingMarkDownToOrderCanRetrieveMarkDownByName() {
		
		MarkDown markDown = new MarkDown();
		markDown.setItemName("Steak");
		markDown.setPriceReduction(new BigDecimal(1.50));
		
		order.addMarkDown(markDown);
		
			
		assertEquals("Steak", order.getMarkDownByName("Steak").getItemName());
		
	}
	
	
	@Test
	public void whenAddingMarkDownForItemTotalReflectsPriceMinusMarkDown() {
		
		MarkDown markDown = new MarkDown();
		markDown.setItemName("Steak");
		markDown.setPriceReduction(new BigDecimal(1.50));
		
		order.addMarkDown(markDown);
		

		order.addItem(new Item("Steak", 3.50));
		
		
		
		assertThat(new BigDecimal(2.00), Matchers.comparesEqualTo(order.total()));
		
	}
	
	
	@Test
	public void whenAddingPercentOffSpecialCanBeRetrievedByItemName() {
		
		PercentOffSpecial percentOffSpecial = new PercentOffSpecial();
		
		percentOffSpecial.setItemName("Yoohoo");
		percentOffSpecial.setPercentOff(15);
		percentOffSpecial.setRequiredNumberOfItems(2);
		
		order.addPercentOffSpecial(percentOffSpecial);
		
		assertEquals("Yoohoo", order.getPercentOffSpecialByName("Yoohoo").getItemName());
		assertEquals(15, order.getPercentOffSpecialByName("Yoohoo").getPercentOff());
	}
	
	
	
	@Test 
	public void whenAddingPercentOffSpecialOfBuy1Get1FreePricesForSecondItemIsZero() {
		
		PercentOffSpecial percentOffSpecial = new PercentOffSpecial();
		
		percentOffSpecial.setItemName("Yoohoo");
		percentOffSpecial.setPercentOff(100); 
		percentOffSpecial.setRequiredNumberOfItems(1);
		percentOffSpecial.setNumberOfItemsEligible(1);
		
		order.addPercentOffSpecial(percentOffSpecial);
		
		order.addItem(new Item("Yoohoo", 5.00));
		order.addItem(new Item("Yoohoo", 5.00));
		
		order.applyPercentOffSpecials();
		
		List<Item> yoohooItems = order.getItemList();
		
		BigDecimal priceItem1 = yoohooItems.get(0).getPrice();
		BigDecimal priceItem2 = yoohooItems.get(1).getPrice();
		
		
		assertThat(new BigDecimal(5.00), Matchers.comparesEqualTo(priceItem1));
		assertThat(BigDecimal.ZERO, Matchers.comparesEqualTo(priceItem2));
		
		
	}
	
	
	@Test 
	public void whenAddingPercentOffSpecialOfBuy2Get1HalfOffPriceForThirdItemIsHalf() {
		
		PercentOffSpecial percentOffSpecial = new PercentOffSpecial();
		
		percentOffSpecial.setItemName("Yoohoo");
		percentOffSpecial.setPercentOff(50); 
		percentOffSpecial.setRequiredNumberOfItems(2);
		percentOffSpecial.setNumberOfItemsEligible(1);
		
		order.addPercentOffSpecial(percentOffSpecial);
		
		order.addItem(new Item("Yoohoo", 5.00));
		order.addItem(new Item("Yoohoo", 5.00));
		order.addItem(new Item("Yoohoo", 5.00));
		
		order.applyPercentOffSpecials();
		
		List<Item> yoohooItems = order.getItemList();
		
		List<Item> itemsAt5Dollars = yoohooItems.stream().filter(item->item.getPrice().compareTo(new BigDecimal(5.00))==0).collect(Collectors.toList());
		Optional<Item> halfOffItem = yoohooItems.stream().filter(item->item.getPrice().compareTo(new BigDecimal(2.50))==0).findFirst();
		assertEquals(2, itemsAt5Dollars.size()); // we have two items at 5.00
		
		assertTrue(halfOffItem.isPresent());
		assertThat(new BigDecimal(2.50), Matchers.comparesEqualTo(halfOffItem.get().getPrice()));
		
		
	}
	
	
	@Test 
	public void whenPercentOffSpecialOfBuy2Get1HalfOffPriceExistsAnd3ItemsAreInOrderThenOrderTotalReflectsDiscount() {
		
		PercentOffSpecial percentOffSpecial = new PercentOffSpecial();
		
		percentOffSpecial.setItemName("Yoohoo");
		percentOffSpecial.setPercentOff(50); 
		percentOffSpecial.setRequiredNumberOfItems(2);
		percentOffSpecial.setNumberOfItemsEligible(1);
		
		order.addPercentOffSpecial(percentOffSpecial);
		
		order.addItem(new Item("Yoohoo", 5.00));
		order.addItem(new Item("Yoohoo", 5.00));
		order.addItem(new Item("Yoohoo", 5.00));
		
		assertThat(new BigDecimal(12.50), Matchers.comparesEqualTo(order.total()));
		
		
	}
	
	@Test
	public void whenAddingItemsThenCallingClearOrderCausesTotalPriceToBeZero() {
		
		order.addItem(new Item("Yoohoo", 5.00));
		order.addItem(new Item("Yoohoo", 5.00));
		
		order.clearOrder();
		
		assertThat(BigDecimal.ZERO, Matchers.comparesEqualTo(order.total()));
		
	}
	
	
	
}
