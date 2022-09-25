package com.crud;

import java.time.LocalDate;

public class Blog {
	private int id;
	private String title;
	private String author;
	private LocalDate date;
	private String text;
	private int authorID;
	
	public Blog () {}
	
	public Blog (int id, String title, String author, LocalDate date, String text, int authorID) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.date = date;
		this.text = text;
		this.authorID = authorID;
	}
	
	public int getauthorID() {
		return authorID;
	}

	public void setauthorID(int authorID) {
		this.authorID = authorID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
