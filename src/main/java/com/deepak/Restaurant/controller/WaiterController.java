package com.deepak.Restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
//This controller 
@Controller
@RequestMapping("/waiter")
public class WaiterController {

	
	 @GetMapping("/addOrders/{tableId}")
	    public String takeOrder(@PathVariable String tableId, Model model) {
//	        ((Object) model).addAttribute("tableId", tableId);
		 model.addAttribute("tableId", tableId);
	        return "addOrders";
	    }
	 
	 @GetMapping("/dashboard")
	 public String dashboard() {
		 
		 return "waiter";
	 }
}
