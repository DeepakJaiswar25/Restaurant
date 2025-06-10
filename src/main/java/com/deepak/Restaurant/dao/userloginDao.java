package com.deepak.Restaurant.dao;



import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

import com.deepak.Restaurant.entity.orders;
import com.deepak.Restaurant.entity.userLogin;
import com.deepak.Restaurant.rowmapper.orderRowMapper;
import com.deepak.Restaurant.rowmapper.userRowMapper;

import jakarta.mail.internet.MimeMessage;


@Repository
public class userloginDao {
	@Autowired
	private JdbcTemplate jdbctemplate;
	@Autowired
	JavaMailSender mailSender;
	//This method is used to save newly created employee data
	   public int saveUser(userLogin user) {
	        String sql = "INSERT INTO user_login (name, email, password,role,EmployeeID) VALUES (?,?,?,?,?)";
	        return jdbctemplate.update(sql, user.getName(),user.getEmail(), user.getPassword(), user.getRole(),user.getEmployeeID());
	    }
	   // This method is employee to fetch user with email
	   public userLogin getUserByEmail(String email) {
		 String sql="Select name, email, password,role from user_login where email=?";
		 System.out.println(sql);
		
//		  Object user = jdbctemplate.queryForObject(sql, new Object[]{email},new userRowMapper());
		 userLogin user = jdbctemplate.queryForObject(sql,new userRowMapper(),email);
		 
		 return  user;
		   
		   
	   }
	   // Thid method is used to fetch employee from employee Id
	   public userLogin getUserByEmployeeID(String employeeID) {
			 String sql="Select name, email, password,role,employeeid from user_login where employeeid=?";
			 System.out.println(sql);
			 userLogin user = jdbctemplate.queryForObject(sql,new userRowMapper(),employeeID);
			 
			 return  user;
			   
			   
		   }
	   // This method is used to send mail for new user when he is signing up. he gets mail with generated Employee Id
	   public void sendMail(String name,String email, String URL,String id) {
			
			String from="deepakj2597@gmail.com";
			String to=email;
			String subject="Employee ID";
			String content="Dear [[name]],<br>"
					+ "Employee Id : [[id]]. <br>"
					+ "please click the link below to login: <br>"
					+ "<h3><a href='[[URL]]'>Login</a></h3>"
					+ "Thank You, <br>"
					+ "Deepak";
//		   
			try {
				 MimeMessage message= mailSender.createMimeMessage();
				 MimeMessageHelper helper= new MimeMessageHelper(message);
				 helper.setFrom(from);
				 helper.setTo(to);
//				 helper.setCc(from);
				 helper.setSubject(subject);
//				 String siteurl=URL+"/verify?code="+user.getVerificationCode();
//				 System.out.println(siteurl);
				 content=content.replace("[[name]]", name);
				 content=content.replace("[[id]]", id);
				 
				 content=content.replace("[[URL]]", URL);
				 helper.setText(content,true);
				 mailSender.send(message);
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
			}
		}
	   //This method is used to generate Id for user
	   public String generateID(String name) {
			String[] names = name.split(" ");
			String fname=names[0];
			String lname= names[1];
			Random random =new Random();
			int nextLong = random.nextInt(0,100000000);
			System.out.println(nextLong);
			String EmployeeID= fname.charAt(0)+""+ lname.charAt(0)+""+nextLong;
			System.out.println(EmployeeID);
			return EmployeeID;
	   }
	  
	   
	 
}
