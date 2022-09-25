package com.crud;

public class User {
	private int id;
	private String name;
	private String title;
	private String img;
	
	public User(int id, String name, String title, String img) {
		this.id = id;
		this.name = name;
		this.title = title;
		this.img = img;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	
	
}
