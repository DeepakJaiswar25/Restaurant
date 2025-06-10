package com.deepak.Restaurant.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.deepak.Restaurant.entity.orders;
import com.deepak.Restaurant.rowmapper.orderRowMapper;
@Repository
public class ordersDao {
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	//This method is used to fetch all orders and show chef to prepare with the food items with highest priority in top
	 public List<orders> getOrders() {
		 String sql="Select * from orders order by priority desc";
		 System.out.println(sql);
		
		 List<orders> order = jdbctemplate.query(sql,new orderRowMapper());
		 return  order;
	   }
   
	 // This method deletes order when done
		public int deletOrder(int id,String status) {
			   String sql="delete from orders  where ordernumber=?";
			   int update = jdbctemplate.update(sql, id);
			   return update;
			}
		
		//This method updates status from pending to in-progress or from in-progress to done
	public int updateOrderStatus(int id,String status) {
		   String sql="update orders set status=? where ordernumber=?";
		   int update = jdbctemplate.update(sql, status,id);
		   return update;
		   
	   }
	//This method updates priority for chef to see orders with high priority
	   public int updateOrderPriority(int id,int priority) {
		   String sql="update orders set priority=? where ordernumber=?";
		   int update = jdbctemplate.update(sql, priority,id);
		   return update;
		   
	   }
}
