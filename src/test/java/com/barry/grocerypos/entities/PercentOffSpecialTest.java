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
		
		List<Item> itemList = new ArrayList<>();
		
		itemList.add(new Item("Yoohoo", 10.00));
		
		
		assertFalse(percentOffSpecial.qualifiesForPercentOffSpecial(itemList));
		
	}
	
	
	@Test 
	public void whenPercentageOffSpecialRequires2ItemsAnd2ExistsQualifiesForSpecialReturnsTrue() {
		
		PercentOffSpecial percentOffSpecial = new PercentOffSpecial();
		
		percentOffSpecial.setItemName("Yoohoo");
		percentOffSpecial.setPercentOff(75); 
		percentOffSpecial.setRequiredNumberOfItems(2);
		

		List<Item> itemList = new ArrayList<>();
		
		itemList.add(new Item("Yoohoo", 10.00));
		itemList.add(new Item("Yoohoo", 10.00));
		
		
		assertTrue(percentOffSpecial.qualifiesForPercentOffSpecial(itemList));

		
	}
	
	
	@Test
	public void whenNumberOfItemsEligibleIsOneThenDiscountIsAppliedToOnlyOneItem() {
		
		PercentOffSpecial percentOffSpecial = new PercentOffSpecial();
		
		percentOffSpecial.setItemName("Yoohoo");
		percentOffSpecial.setPercentOff(75); 
		percentOffSpecial.setRequiredNumberOfItems(2);
		percentOffSpecial.setNumberOfItemsEligible(1);
		
		List<Item> itemList = new ArrayList<>();
		
		itemList.add(new Item("Yoohoo", 10.00));
		itemList.add(new Item("Yoohoo", 10.00));
		itemList.add(new Item("Yoohoo", 10.00));
		
		percentOffSpecial.applySpecial(itemList);
		
		int numberOfDiscountedItems = (int) itemList.stream().filter(item->item.getPrice().compareTo(new BigDecimal(2.50))==0).count();
		int numberOfNonDiscountedItems = (int) itemList.stream().filter(item->item.getPrice().compareTo(new BigDecimal(10.00))==0).count();
		assertEquals(1, numberOfDiscountedItems);
		assertEquals(2, numberOfNonDiscountedItems);
		
		
		
	}
	

	
}
