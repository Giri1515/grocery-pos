package com.barry.grocerypos.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InventoryControllerTests {
	
	@Autowired
	private MockMvc mockMvc;


	
	@Test
	public void whenPostingToInventoryItemsURISomethingIsListening() throws Exception {
		
		mockMvc.perform(post("/inventory/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content(""))
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
	
}
