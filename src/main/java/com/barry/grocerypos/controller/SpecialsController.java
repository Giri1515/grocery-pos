package com.barry.grocerypos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/specials")
public class SpecialsController {
	
	@RequestMapping(value = "/markdowns", method=POST)
	public String addMarkDown() {
		
		return "";
	}

}
