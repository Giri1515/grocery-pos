package com.barry.grocerypos.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.barry.grocerypos.entities.MarkDown;
import com.barry.grocerypos.entities.Order;
import com.barry.grocerypos.entities.Special;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpecialsControllerTests {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@SpyBean
	private Order order;
	
	@Test
	public void whenPostingToTheMarkDownsURIWithValidRequestCanGetASuccessfullResponse() throws Exception {
		
		
		String specialJSON = "{\"itemName\":\"Bacon\", \"priceReduction\":1.45}";
		
		mockMvc.perform(post("/specials/markdowns")
				.contentType(MediaType.APPLICATION_JSON)
				.content(specialJSON))
				.andExpect(status().isOk());
		
	}
	
	@Test
	public void whenPostingToTheMarkDownsURIWithValidRequestMarkDownIsAddedToOrder() throws Exception {
		
		
		String specialJSON = "{\"itemName\":\"Bacon\", \"priceReduction\":1.45}";
		
		doNothing().when(order).addMarkDown((Mockito.any(MarkDown.class)));
		
		mockMvc.perform(post("/specials/markdowns")
				.contentType(MediaType.APPLICATION_JSON)
				.content(specialJSON))
				.andExpect(status().isOk());
		
		
		verify(order).addMarkDown((Mockito.any(MarkDown.class)));
	}
	
	
	@Test
	public void whenPostingToTheMarkDownsURIWithValidRequestSuccessMessageIsReturned() throws Exception {
		
		
		String specialJSON = "{\"itemName\":\"Bacon\", \"priceReduction\":1.45}";
		
		
		mockMvc.perform(post("/specials/markdowns")
				.contentType(MediaType.APPLICATION_JSON)
				.content(specialJSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(".message", hasItem("MarkDown Successfully Added")));
		
	}
	
	
	@Test
	public void whenPostingToTheBuyXForPriceURIWithValidRequestReturnsSuccessfulResponse() throws Exception {
		
		
		String specialJSON = "{\"itemName\":\"Eggs\", \"quantityRequired\":3, \"totalPrice\":5.00}";
		
		mockMvc.perform(post("/specials/buyXForPrice")
				.contentType(MediaType.APPLICATION_JSON)
				.content(specialJSON))
				.andExpect(status().isOk());
		
	}
	
	
	@Test
	public void whenPostingToTheBuyXForPriceURIWithValidRequestReturnsSuccessfulJSONResponseMessage() throws Exception {
		
		
		String specialJSON = "{\"itemName\":\"Eggs\", \"quantityRequired\":3, \"totalPrice\":5.00}";
		
		
		mockMvc.perform(post("/specials/buyXForPrice")
				.contentType(MediaType.APPLICATION_JSON)
				.content(specialJSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(".message", hasItem("Special Successfully Added")));
		
	}
	
	@Test
	public void whenPostingToTheBuyXForPriceURIWithValidRequestSpecialIsAddedToOrder() throws Exception {
		
		String specialJSON = "{\"itemName\":\"Eggs\", \"quantityRequired\":3, \"totalPrice\":5.00}";
		
		doNothing().when(order).addSpecial((Mockito.any(Special.class)));
		
		mockMvc.perform(post("/specials/buyXForPrice")
				.contentType(MediaType.APPLICATION_JSON)
				.content(specialJSON))
				.andExpect(status().isOk());
		
		
		verify(order).addSpecial((Mockito.any(Special.class)));
	}
	
	

}
