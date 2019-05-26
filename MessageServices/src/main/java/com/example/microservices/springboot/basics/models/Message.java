package com.example.microservices.springboot.basics.models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.stereotype.Component;

@XmlRootElement
@Component
public class Message {

	private int id;
	private String author;
	private String message;
	private Date created;
	private int likes;
	private int dislikes;
	private Map<Integer,Comments> comments = new HashMap<Integer,Comments>();
	
	public Message() {
		
	}
	
	public Message(String author, String message) {
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
	public void setComments(Map<Integer, Comments> comments) {
		this.comments = comments;
	}
	public Map<Integer, Comments> getComments() {
		return comments;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", author=" + author + ", message=" + message + ", created=" + created + ", likes="
				+ likes + ", dislikes=" + dislikes + ", comments=" + comments + "]";
	}
	
	
}
