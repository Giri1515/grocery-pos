package com.barry.grocerypos.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;

public class SpecialTest {
	
	
	
	@Test 
	public void specialCanBeSetWithNameQuantityAndPriceToSupportNumForXPrice() {
		
		Special special = new Special();
		
		special.setItemName("Bacon");
		special.setQuantityRequired(3);
		special.setTotalPrice(new BigDecimal(5.00));
		
		assertEquals("Bacon", special.getItemName());
		assertEquals(3, special.getQuantityRequired());
		assertEquals(new BigDecimal(5.00), special.getTotalPrice());
	}
	
	
	@Test
	public void givenQuantityOf2AndTotalPriceOf5ThenUnitPriceWillBeTwoFifty() {
		
		Special special = new Special();
		
		special.setItemName("Tater Tots");
		special.setQuantityRequired(2);
		special.setTotalPrice(new BigDecimal(5.00));
		
		assertThat(new BigDecimal(2.50), Matchers.comparesEqualTo(special.getUnitPrice()));
		
	}
	
	@Test
	public void givenQuantityOf3AndTotalPriceOf5ThenUnitPriceWillHaveAScaleOfThreeToHandlePrecision() {
		
		Special special = new Special();
		
		special.setItemName("Pop Tarts");
		special.setQuantityRequired(3);
		special.setTotalPrice(new BigDecimal(5.00));
		
		
		assertEquals(3, special.getUnitPrice().scale());
		assertThat(new BigDecimal(1.667).setScale(3, RoundingMode.HALF_UP), Matchers.comparesEqualTo(special.getUnitPrice()));
		
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

}
