package com.barry.grocerypos.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ScannerTest {

	
	@Test
	public void scanningItemIncreasesOrderSize() {
		
		Scanner scanner = new Scanner();
		
		scanner.scanItem("Bacon");
		
		assertEquals(1, scanner.getOrder().getSize());
		
		
		
	}
	
	
}
