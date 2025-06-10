package com.deepak.Restaurant.entity;
// This is entity class for orders
public class orders {
	private int id;
	private String name;
	private String status;
	private int priority;
	private String tableNumber;
	private int quantity;
	private String comment;

	public orders() {
		super();
	}

	public orders(int id, String name, String status, int priority, String tableNumber, String comment,int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.priority = priority;
		this.tableNumber = tableNumber;
		this.comment = comment;
		this.quantity=quantity;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "orders [id=" + id + ", name=" + name + ", status=" + status + ", priority=" + priority
				+ ", tableNumber=" + tableNumber + ", comment=" + comment + ", quantity=" + quantity + "]";
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}
}
