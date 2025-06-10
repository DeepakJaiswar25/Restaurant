package com.deepak.Restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.Restaurant.dao.foodmenudao;
import com.deepak.Restaurant.entity.UserEntity;
import com.deepak.Restaurant.entity.foodmenu;
import com.deepak.Restaurant.entity.orders;
import com.deepak.Restaurant.entity.userLogin;
//This is the rest api controller where it contains all the api methods.
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ApiController {
	@Autowired
	foodmenudao dao;
	
	//This api gets all the menu items from database.
	@GetMapping("/getMenu")
	public List<foodmenu> getMenu() {
	
		return dao.getMenuItems();
	}
	
	//This api gets all the employees from database.
	@GetMapping("/getEmployees")
	public List<UserEntity> getEmployees() {
	
		return dao.getEmployees();
	}
	//This api helps to get the manager
	@GetMapping("/getManager")
	public List<UserEntity> getManager() {
	
		return dao.getManager();
	}
	//This api is used to save the items in the cart and the food items will be visible to chef.
	@PostMapping("/saveorder")
	public ResponseEntity<String> saveOrder(@RequestBody List<orders> items){
		System.out.print("Inside saveOrder Method");
		for(orders order: items) {
			
        dao.saveOrder(order);
		}
		 return ResponseEntity.ok("Cart saved successfully");
		
	}
	
	//This api helps us to save the new order that we want to add in the menu items
	@PostMapping("/saveMenu")
	public ResponseEntity<String> saveMenu(@RequestBody foodmenu food){
		System.out.print("Inside saveMenu Method");
		
			
        dao.saveMenu(food);
		
		 return ResponseEntity.ok("Menu saved successfully");
		
	}
	// This api will save the new employee to database.
	@PostMapping("/saveEmployee")
	public ResponseEntity<String> saveEmployee(@RequestBody userLogin employee){
		System.out.print("Inside saveEmployee Method");
		
			
        dao.saveEmployee(employee);
		
		 return ResponseEntity.ok("Employee saved successfully");
		
	}
	//This api will update the employee we want to update with the new details.
	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<String> updateEmployee(@RequestBody userLogin employee,@PathVariable String id){
		System.out.print("Inside updateEmployee Method");
		
			
        dao.updateEmployee(employee,id);
		
		 return ResponseEntity.ok("Employee saved successfully");
		
	}
	//This api will update the menu we want to make some changes
	@PutMapping("/updateMenu/{id}")
	public ResponseEntity<String> updateMenu(@RequestBody foodmenu menu,@PathVariable String id){
		System.out.print("Inside updateEmployee Method");
		
			
        dao.updateMenu(menu,id);
		
		 return ResponseEntity.ok("Menu saved successfully");
		
	}
	//This api will delete the employee we want
	@DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id) {
		System.out.print("Inside deleteEmployee Method");
		
		int deleteEmployee = dao.deleteEmployee(id);
		return ResponseEntity.ok("Employee deleted successfully");
	}
	//This api will delete the menu we want.
	@DeleteMapping("/deleteMenu/{id}")
    public ResponseEntity<String> deleteMenu(@PathVariable String id) {
		System.out.print("Inside deleteEmployee Method");
		
		int deleteMenu= dao.deleteMenu(id);
		return ResponseEntity.ok("Employee deleted successfully");
	}
	
	
}

