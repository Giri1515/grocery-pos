package com.barry.grocerypos.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.barry.grocerypos.entities.Inventory;
import com.barry.grocerypos.entities.Item;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	Inventory inventory;
	
	@RequestMapping(value = "/items", method = POST, produces= "application/json")
	public Item addItems(@RequestBody Item item) {
		inventory.addItem(item.getName(), item.getPrice().doubleValue());
		return item;
	}
	
	@RequestMapping(value = "items", method = GET, produces= "application/json")
	@ResponseBody
	public List<Item> getItems() throws Exception{

       return inventory.getItemMap().values().stream().collect(Collectors.toList());

   }
	
}
