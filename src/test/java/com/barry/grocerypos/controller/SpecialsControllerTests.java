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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpecialsControllerTests {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void whenPostingToTheMarkDownsURIWithValidRequestCanGetASuccessfullResponse() throws Exception {
		
		
		String specialJSON = "{\"itemName\":\"Bacon\", \"amountOff\":1.45}";
		
		mockMvc.perform(post("/specials/markdowns")
				.contentType(MediaType.APPLICATION_JSON)
				.content(specialJSON))
				.andExpect(status().isOk());
		
	}
	

}
