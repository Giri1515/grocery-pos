package com.barry.grocerypos.entities;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

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

	
}
