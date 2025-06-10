package com.deepak.Restaurant.entity;

public class table {
	  private int tableNumber;
	    private String size;
		public int getTableNumber() {
			return tableNumber;
		}
		public void setTableNumber(int tableNumber) {
			this.tableNumber = tableNumber;
		}
		public String getSize() {
			return size;
		}
		public void setSize(String size) {
			this.size = size;
		}
		@Override
		public String toString() {
			return "table [tableNumber=" + tableNumber + ", size=" + size + "]";
		}
		public table(int tableNumber, String size) {
			super();
			this.tableNumber = tableNumber;
			this.size = size;
		}
		public table() {
			super();
		} 
}
