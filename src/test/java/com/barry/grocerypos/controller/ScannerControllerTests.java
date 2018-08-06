package com.barry.grocerypos.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.barry.grocerypos.entities.Inventory;
import com.barry.grocerypos.entities.Order;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ScannerControllerTests {

	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	Inventory inventory;
	
	@Autowired
	Order order;
	
	@Before
	public void initOrder() {
		order.clearOrder();
	}
	
	@Test
	public void whenPostToScannerItemWithValidPayloadReturnsOK() throws Exception {
		String itemNameJson = "{\"itemName\":\"Bacon\"}";
		
		mockMvc.perform(post("/scanner/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content(itemNameJson))
				.andExpect(status().isOk());
		
	}
	
	@Test
	public void whenPostToScannerItemsURIReturnsJSONContentTypeInHeader() throws Exception {
		String itemNameJson = "{\"itemName\":\"Bacon\"}";
	
		mockMvc.perform(post("/scanner/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content(itemNameJson))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
				
		
	}
	
	@Test
	public void whenBaconAddedToInventoryAndOnePostToScannerWithBaconThenReturnsPriceOfItemFromInventory() throws Exception {
		String itemNameJson = "{\"itemName\":\"Bacon\"}";
		
		inventory.addItem("Bacon", 3.55);
		
		mockMvc.perform(post("/scanner/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content(itemNameJson))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(".orderTotal", hasItem(3.55)));
				
	}
	
	@Test
	public void whenEggsAddedToInventoryAndOnePostToScannerWithEggThenReturnsPriceOfItemFromInventory() throws Exception {
		String itemNameJson = "{\"itemName\":\"Eggs\"}";
		
		inventory.addItem("Eggs", 2.89);
		
		mockMvc.perform(post("/scanner/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content(itemNameJson))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(".orderTotal", hasItem(2.89)));
				
	}
	
	
	@Test
	public void whenScanItemCalledTwiceForAnItemThenSecondReturnsTwicePriceOfItem() throws Exception {
		String itemNameJson = "{\"itemName\":\"Eggs\"}";
		
		inventory.addItem("Eggs", 2.50);
		
		mockMvc.perform(post("/scanner/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content(itemNameJson))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(".orderTotal", hasItem(2.50)));
		
		mockMvc.perform(post("/scanner/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content(itemNameJson))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(".orderTotal", hasItem(5.00)));
				
	}
	
}
