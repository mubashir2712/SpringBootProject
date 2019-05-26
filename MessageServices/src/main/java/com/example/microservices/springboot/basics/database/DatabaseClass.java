package com.example.microservices.springboot.basics.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.microservices.springboot.basics.models.Message;

public class DatabaseClass {
	
	
	private static Map<Integer,Message> messages = new HashMap<Integer,Message>();
	
	
	public static Map<Integer,Message> getMessages(){
		if(messages.isEmpty()) {
		   messages.put(1, new Message("Mubashir","Moby"));
		   messages.put(2, new Message("Koushik","Java Brains"));
		}
		return messages; 
	}
}