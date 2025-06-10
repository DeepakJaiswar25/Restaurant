package com.deepak.Restaurant.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deepak.Restaurant.dao.ordersDao;
import com.deepak.Restaurant.dao.userloginDao;
import com.deepak.Restaurant.entity.orders;
import com.deepak.Restaurant.entity.userLogin;
//This controller is responsible to login and signup
@Controller
public class HomeController {
	
	@Autowired
	private userloginDao dao;
	 
    
	
	@Autowired
	private ordersDao orddao;

	@GetMapping("/")
	public String index() {
		return "index";	
	}
	@GetMapping("/waiter")
	public String waiter() {
		return "waiter";	
	}
	@GetMapping("/owner")
	public String owner() {
		return "owner";	
	}
	@GetMapping("/payment")
	public String payment() {
		return "payment";	
	}
	@GetMapping("/manager")
	public String manager() {
		return "manager";	
	}
	@GetMapping("/chef")
	public String chef(Model model) {
		List<orders> orders = orddao.getOrders();
		model.addAttribute("orders",orders);
		return "chefloginsuccess";	
	}
	@GetMapping("/host")
	public String host() {
		return "hostloginsuccess";	
	}
	// This method is called when we hit signup button to create new user.
	@PostMapping("/createUser")
	public String createUser(@ModelAttribute userLogin user){
		System.out.println("Data:"+user);
		String id = dao.generateID(user.getName());
		user.setEmployeeID(id);
		String encodedPassword = Base64.getEncoder().encodeToString(user.getPassword().getBytes());
		System.out.println(encodedPassword);
		user.setPassword(encodedPassword);
		String URL="http://localhost:9090/";
		System.out.println("In Registration Method");
		int login=dao.saveUser(user);
		dao.sendMail(user.getName(),user.getEmail(), URL, id);
		System.out.println(login);
		if(login ==1) {
			return "index";
		}
		
		return "error";
	}
	
	//This method is called when the user tries to login
	@PostMapping("/loginUser")
	public String loginUser(@RequestParam String employeeid,@RequestParam String password,RedirectAttributes redirectAttributes,Model model){
		System.out.println("employeeID:"+employeeid);
		System.out.println("password:"+password);
		
		
		System.out.println("In Login Method");
		userLogin login=dao.getUserByEmployeeID(employeeid);
//		userLogin login=dao.getUserByEmail(email);
		byte[] decode = Base64.getDecoder().decode(login.getPassword());
		String decodedpassword = new String(decode);
		if(login.getEmployeeID().equalsIgnoreCase(employeeid) && decodedpassword.equalsIgnoreCase(password) && login.getRole().equalsIgnoreCase("Chef")) {
			List<orders> orders = orddao.getOrders();
			model.addAttribute("orders",orders);
			model.addAttribute("name", login.getName());
			return "chefloginsuccess";
		}
		else if(login.getEmployeeID().equalsIgnoreCase(employeeid) && decodedpassword.equalsIgnoreCase(password) && login.getRole().equalsIgnoreCase("Manager")) {
			model.addAttribute("name", login.getName());
			return "manager";
		} 
		else if(login.getEmployeeID().equalsIgnoreCase(employeeid) && decodedpassword.equalsIgnoreCase(password) && login.getRole().equalsIgnoreCase("Host")) {
			model.addAttribute("name", login.getName());
			return "hostloginsuccess";
		}
		else if(login.getEmployeeID().equalsIgnoreCase(employeeid) && decodedpassword.equalsIgnoreCase(password) && login.getRole().equalsIgnoreCase("Waiter")) {
			model.addAttribute("name", login.getName());
			return "waiter";
		}
		else if(login.getEmployeeID().equalsIgnoreCase(employeeid) && decodedpassword.equalsIgnoreCase(password) && login.getRole().equalsIgnoreCase("Owner")) {
			model.addAttribute("name", login.getName());
			return "owner";
		}
		else if(login.getEmployeeID().equalsIgnoreCase(employeeid) && decodedpassword.equalsIgnoreCase(password) && login.getRole().equalsIgnoreCase("Admin")) {
			model.addAttribute("name", login.getName());
			return "admin";
		}
		else {
			redirectAttributes.addFlashAttribute("msg", "Invalid Credentials");
			return "redirect:/error";
		}
		
	}
	
}
