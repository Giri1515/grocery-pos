package com.barry.grocerypos.entities;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
	
	

}
