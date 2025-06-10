package com.deepak.Restaurant.entity;

public class userLogin {
	
	private String name;
	private String email;
	private String password;
	private String employeeid;
	public userLogin() {
		super();
	}

	private String role;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public userLogin(String name, String email, String password, String role,String employeeid) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.employeeid=employeeid;
	}
	@Override
	public String toString() {
		return "userLogin [name=" + name + ", email=" + email + ", password=" + password + ", employeeid=" + employeeid
				+ ", role=" + role + "]";
	}
	public String getEmployeeID() {
		return employeeid;
	}
	public void setEmployeeID(String employeeid) {
		this.employeeid = employeeid;
	}
}
