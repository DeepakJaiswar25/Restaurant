package com.deepak.Restaurant.entity;

public class UserEntity {

	private String employeeid;
	private String name;
	private String role;
	private String email;
	
	
	public UserEntity() {
		super();
	}
	public UserEntity(String name, String role,String employeeid,String email) {
		super();
		this.employeeid=employeeid;
		this.name = name;
		this.role = role;
		this.email=email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "UserEntity [employeeid=" + employeeid + ", name=" + name + ", role=" + role + ", email=" + email + "]";
	}
	
	
}
