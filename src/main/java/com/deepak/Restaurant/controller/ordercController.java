package com.deepak.Restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deepak.Restaurant.dao.ordersDao;
//This controller is responsible to update order status and order priority in chef dashboard.
@Controller
@RequestMapping("/orders")
public class ordercController {
	
	@Autowired
	private ordersDao orddao;
	// This method is called to update status and it deletes when the status is done
	@PutMapping("/update-status/{orderId}/{updateStatus}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable int orderId, @PathVariable String updateStatus) {
		
		if(updateStatus.equalsIgnoreCase("Done")) {
			System.out.print("Inside order delete method");
			orddao.deletOrder(orderId, updateStatus);
		}
		else {
			 orddao.updateOrderStatus(orderId, updateStatus);
		}
       
        return ResponseEntity.ok("Order status updated successfully.");
    }
// Thus method updates priority
    @PutMapping("/update-priority/{orderId}/{updatePriority}")
    public ResponseEntity<?> updateOrderPriority(@PathVariable int orderId, @PathVariable int updatePriority) {
        orddao.updateOrderPriority(orderId, updatePriority);
        return ResponseEntity.ok("Order priority updated successfully.");
    }
	

}
