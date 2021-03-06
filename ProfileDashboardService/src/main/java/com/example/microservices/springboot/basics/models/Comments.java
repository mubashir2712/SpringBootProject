package com.example.microservices.springboot.basics.models;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;


@XmlRootElement
@Component
public class Comments {

	private int id;
	private String author;
	private String message;
	private Date created;
	
	public Comments() {
		
	}
	
	public Comments(int id, String author, String message) {
		this.id = id;
		this.author = author;
		this.message = message;
		this.created = new Date();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
}
