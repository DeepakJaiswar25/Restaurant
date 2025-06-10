package com.deepak.Restaurant.dao;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.deepak.Restaurant.entity.UserEntity;
import com.deepak.Restaurant.entity.foodmenu;
import com.deepak.Restaurant.entity.orders;
import com.deepak.Restaurant.entity.userLogin;
import com.deepak.Restaurant.rowmapper.UserEntityMapper;
import com.deepak.Restaurant.rowmapper.menuRowMapper;
import com.deepak.Restaurant.rowmapper.userRowMapper;

@Repository
public class foodmenudao {
	@Autowired
	private userloginDao dao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	// This method gets all menu items from database 
	public List<foodmenu> getMenuItems() {
		String sql= "Select * from food_menu";
		 List<foodmenu> menu = jdbcTemplate.query(sql,new menuRowMapper());
		 return  menu;
	}
//	this method saves order in orders table.
	 public int saveOrder(orders order) {
		 
		   String name=order.getName();
		   String table=order.getTableNumber();
		   String tableNumber=table.substring(5, 7);
		   int quantity=order.getQuantity();
		   String comments= order.getComment();
		   String status ="Pending";
		   int priority=0;
		   if(comments==null) {
			   comments= "No Comments";
		   }
	        String sql = "INSERT INTO orders (tablenumber,items,quantity,foodcomments,status,priority) VALUES (?,?,?,?,?,?)";
	        return jdbcTemplate.update(sql, tableNumber,name,quantity, comments,status,priority);
	      
	    }
	 
	 //This method gets all employees from database except manager
	 public List<UserEntity> getEmployees() {
			String sql= "Select employeeid,name,role,email from user_login where role!='Manager'";
			 List<UserEntity> employees = jdbcTemplate.query(sql,new UserEntityMapper());
			 return  employees;
		}
	 //This method gets manager from database
	 public List<UserEntity> getManager() {
			String sql= "Select employeeid,name,role,email from user_login where role='Manager'";
			 List<UserEntity> employees = jdbcTemplate.query(sql,new UserEntityMapper());
			 return  employees;
		}
	// This method add new staff or manager to database
	 public int saveEmployee(userLogin user) {
		 String name=user.getName();
		 String role=user.getRole();
		 String mail=user.getEmail();
		 String password="user";
		 String encodedpassword=Base64.getEncoder().encodeToString(password.getBytes());
		 String empid = dao.generateID(user.getName());
	     String sql = "INSERT INTO user_login (name,email,password, role,employeeid) VALUES (?,?,?,?,?)";
	     return jdbcTemplate.update(sql, name,mail,encodedpassword, role,empid);
	   
	 }
	 // This method add new food items in the menu items
	 public int saveMenu(foodmenu food) {
		String foodname=food.getName();
		String price = food.getPrice();
		String desc= food.getDesc();
		String  image= food.getImageUrl();
		String category =food.getCategory();
		
	     String sql = "INSERT INTO food_menu (food_name,price,description,imageurl,category) VALUES (?,?,?,?,?)";
	     return jdbcTemplate.update(sql, foodname,price,desc,image,category);
	   
	 }
	//This method updates employee with the updated details
	 public int updateEmployee(userLogin user, String id) {
		 String name=user.getName();
		 String role=user.getRole();
		 String mail=user.getEmail();
		
		 String employeeid=id;
	     String sql = "UPDATE user_login SET name = ?, email = ?,role=? where employeeid=?";
	     return jdbcTemplate.update(sql, name,mail,role,employeeid);
	  
	 }
	 // This method updates menu with the updated details for particular food item 
	 public int updateMenu(foodmenu menu, String id) {
		 String name= menu.getName();
		 String foodname= menu.getName();
		 String price= menu.getPrice();
		 String desc= menu.getDesc();
		 String image= menu.getImageUrl();
		 String category= menu.getCategory();
		 
	     String sql = "UPDATE food_menu SET food_name = ?, price = ?,description=?,imageurl=?,category=? where food_name=?";
	     System.out.println(sql);
	     return jdbcTemplate.update(sql, name,price,desc,image,category,foodname);
	  
	 }
	 //This method deletes employee 
	 public int deleteEmployee(String id) {
		 String sql="Delete from user_login where employeeid=?";
		 return jdbcTemplate.update(sql, id);
	 }
	//This method deletes Menu
	 public int deleteMenu(String id) {
		 String sql="Delete from food_menu where food_name=?";
		 return jdbcTemplate.update(sql, id);
	 }
}

