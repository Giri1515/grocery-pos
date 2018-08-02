package com.barry.grocerypos.entities;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;

public class PercentOffSpecialTest {
	
	@Test
	public void canHoldItemNameNumOfItemsAndPercentOff() {
		
		PercentOffSpecial percentOffSpecial = new PercentOffSpecial();
		
		percentOffSpecial.setItemName("Twizzlers");
		percentOffSpecial.setRequiredNumberOfItems(3);
		percentOffSpecial.setPercentOff(20);
		
		assertEquals("Twizzlers", percentOffSpecial.getItemName());
		assertEquals(3, percentOffSpecial.getRequiredNumberOfItems());
		assertEquals(20, percentOffSpecial.getPercentOff());
		
	}
	
	@Test
	public void givenAPriceCalcDiscountPriceWillReturnThePriceWithPercentOffApplied() {
		
		PercentOffSpecial percentOffSpecial = new PercentOffSpecial();
		
		percentOffSpecial.setItemName("Twizzlers");
		percentOffSpecial.setRequiredNumberOfItems(3);
		percentOffSpecial.setPercentOff(20);
		
		BigDecimal price = new BigDecimal(10.00);
		
		assertThat(new BigDecimal(8.00), Matchers.comparesEqualTo(percentOffSpecial.calcDiscountPrice(price)));
		
		
	}
	
	@Test 
	public void whenPercentageOffSpecialRequires2ItemsAndOnlyOneExistsQualifiesForSpecialReturnsFalse() {
		
		PercentOffSpecial percentOffSpecial = new PercentOffSpecial();
		
		percentOffSpecial.setItemName("Yoohoo");
		percentOffSpecial.setPercentOff(75); 
		percentOffSpecial.setRequiredNumberOfItems(2);
		
		Item item = new Item();
		item.setName("Yoohoo");
		
		List<Item> itemList = new ArrayList<>();
		
		itemList.add(item);
		
		
		assertFalse(percentOffSpecial.qualifiesForPercentOffSpecial(itemList));
		
	}
	
	
	@Test 
	public void whenPercentageOffSpecialRequires2ItemsAnd2ExistsQualifiesForSpecialReturnsTrue() {
		
		PercentOffSpecial percentOffSpecial = new PercentOffSpecial();
		
		percentOffSpecial.setItemName("Yoohoo");
		percentOffSpecial.setPercentOff(75); 
		percentOffSpecial.setRequiredNumberOfItems(2);
		
		
		Item item1 = new Item();
		item1.setName("Yoohoo");
		

		Item item2 = new Item();
		item2.setName("Yoohoo");


		List<Item> itemList = new ArrayList<>();
		
		itemList.add(item1);
		itemList.add(item2);
		
		
		assertTrue(percentOffSpecial.qualifiesForPercentOffSpecial(itemList));

		
	}
	
	
	@Test
	public void whenNumberOfItemsEligibleIsOneThenDiscountIsAppliedToOnlyOneItem() {
		
		PercentOffSpecial percentOffSpecial = new PercentOffSpecial();
		
		percentOffSpecial.setItemName("Yoohoo");
		percentOffSpecial.setPercentOff(75); 
		percentOffSpecial.setRequiredNumberOfItems(2);
		percentOffSpecial.setNumberOfItemsEligible(1);
		
		
		Item item1 = new Item();
		item1.setName("Yoohoo");
		item1.setPrice(new BigDecimal(10.00));
		

		Item item2 = new Item();
		item2.setName("Yoohoo");
		item2.setPrice(new BigDecimal(10.00));

		Item item3 = new Item();
		item3.setName("Yoohoo");
		item3.setPrice(new BigDecimal(10.00));

		List<Item> itemList = new ArrayList<>();
		
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
		
		percentOffSpecial.applySpecial(itemList);
		
		int numberOfDiscountedItems = (int) itemList.stream().filter(item->item.getPrice().compareTo(new BigDecimal(2.50))==0).count();
		int numberOfNonDiscountedItems = (int) itemList.stream().filter(item->item.getPrice().compareTo(new BigDecimal(10.00))==0).count();
		assertEquals(1, numberOfDiscountedItems);
		assertEquals(2, numberOfNonDiscountedItems);
		
		
		
	}
	

	
}
