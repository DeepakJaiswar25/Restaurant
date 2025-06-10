package com.deepak.Restaurant.entity;
//This is entity class for food menu
public class foodmenu {
	private String category;
	private String name;
	private String price;
	private String desc;
	private String imageUrl;
	public foodmenu() {
		super();
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Override
	public String toString() {
		return "foodmenu [category=" + category + ", name=" + name + ", price=" + price + ", desc=" + desc
				+ ", imageUrl=" + imageUrl + "]";
	}
	public foodmenu(String category, String name, String price, String desc, String imageUrl) {
		super();
		this.category = category;
		this.name = name;
		this.price = price;
		this.desc = desc;
		this.imageUrl = imageUrl;
	}
	
}
