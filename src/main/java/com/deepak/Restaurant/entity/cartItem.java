package com.deepak.Restaurant.entity;
//This is entity class for cart item
public class cartItem {
	    private String foodname;
	    private double price;
	    private int quantity;
	    private String tableName;
		public String getFoodname() {
			return foodname;
		}
		public void setFoodname(String foodname) {
			this.foodname = foodname;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public String getTableName() {
			return tableName;
		}
		public void setTableName(String tableName) {
			this.tableName = tableName;
		}
		@Override
		public String toString() {
			return "cartItem [foodname=" + foodname + ", price=" + price + ", quantity=" + quantity + ", tableName="
					+ tableName + "]";
		}
		public cartItem() {
			super();
		}
		public cartItem(String foodname, double price, int quantity, String tableName) {
			super();
			this.foodname = foodname;
			this.price = price;
			this.quantity = quantity;
			this.tableName = tableName;
		}
}
