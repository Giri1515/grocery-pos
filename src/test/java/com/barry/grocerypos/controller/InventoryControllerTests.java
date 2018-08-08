package com.barry.grocerypos.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.barry.grocerypos.entities.Inventory;
import com.barry.grocerypos.entities.Item;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InventoryControllerTests {
	
	@Autowired
	private MockMvc mockMvc;


	@Autowired
	private Inventory inventory;
	
	@Test
	public void whenPostingToInventoryItemsURISomethingIsListening() throws Exception {
		String itemJSON = "{\"name\":\"Swiss Cake Rolls\",\"price\":2.99}";
		
		mockMvc.perform(post("/inventory/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content(itemJSON))
				.andExpect(status().isOk());
		
	}
	
	@Test
	public void whenPostToInventoryItemsURIReturnsJSON() throws Exception {
		
		String itemJSON = "{\"name\":\"Swiss Cake Rolls\",\"price\":2.99}";

		mockMvc.perform(post("/inventory/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content(itemJSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
				
		
	}
	
	
	@Test
	public void whenPostItemJSONToItemsURIReturnsItemName() throws Exception {
		

		String itemJSON = "{\"name\":\"Swiss Cake Rolls\",\"price\":2.99}";
		
		mockMvc.perform(post("/inventory/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content(itemJSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(".name", hasItem("Swiss Cake Rolls")));
		
	}
	
	@Test
	public void whenPostItemJSONToItemsURIReturnsItemNameAndPrice() throws Exception {
		

		String itemJSON = "{\"name\":\"Swiss Cake Rolls\",\"price\":2.99}";
		
		mockMvc.perform(post("/inventory/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content(itemJSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(".name", hasItem("Swiss Cake Rolls")))
				.andExpect(jsonPath(".price", hasItem(2.99)));
		
	}
	
	
	@Test
	public void whenPostingItemInJSONItemIsAddedToInventory() throws Exception {
		
		String itemJSON = "{\"name\":\"Swiss Cake Rolls\",\"price\":2.99}";
		mockMvc.perform(post("/inventory/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content(itemJSON))
				.andExpect(status().isOk());

		Item item = inventory.getItemByName("Swiss Cake Rolls");
		
		assertNotNull(item);
		assertThat(new BigDecimal(2.99).setScale(2, RoundingMode.HALF_UP), Matchers.comparesEqualTo(item.getPrice()));
		
		
	}
	
	@Test
	public void whenSendingGetRequestToItemsURIReturnsCurrentListOfItems() throws Exception {
		
		inventory.addItem("HoHo", 1.55);
		
		mockMvc.perform(get("/inventory/items")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[*].name", hasItem("HoHo")))
				.andExpect(jsonPath("$[*].price", hasItem(1.55)));
		
		
	}

	
}
