package com.barry.grocerypos.entities;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.Test;

public class MarkDownTest {
	
	@Test
	public void markDownTakesItemNameAndPriceReduction() {
		
		MarkDown markDown = new MarkDown();
		
		markDown.setItemName("Bacon");
		
		markDown.setPriceReduction(new BigDecimal(.20));
		
		assertThat(new BigDecimal(.20), Matchers.comparesEqualTo(markDown.getPriceReduction()));
		assertEquals("Bacon", markDown.getItemName());
	}

}
