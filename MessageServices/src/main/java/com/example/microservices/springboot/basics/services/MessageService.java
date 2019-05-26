package com.example.microservices.springboot.basics.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.example.microservices.springboot.basics.database.DatabaseClass;
import com.example.microservices.springboot.basics.models.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

	private Map<Integer,Message> messages = DatabaseClass.getMessages();
	
    public List<Message> getAllMessages(){
	     return new ArrayList<Message>(messages.values());
    }
    
    public List<Message> getAllMessagesForYear(int year){
	    List<Message> list = new ArrayList<Message>();
    	Calendar cal = Calendar.getInstance();
    	for(Message msg : messages.values()) {
    		cal.setTime(msg.getCreated());
    		if(cal.get(Calendar.YEAR) == year) {
    			list.add(msg);
    		}
    	}
    	return list;
    }
    
    public List<Message> getAllPaginatedMessages(int start,int size){
    	if(start + size > messages.size()) return new ArrayList<Message>(messages.values());
    	return new ArrayList<Message>(messages.values()).subList(start, start + size);
    }
    
    public Message getMessage(int id){
    	return messages.get(id);
    }
    
    public Message addMessage(Message message){
    	message.setId(messages.size() + 1);
    	messages.put(message.getId(),message);
    	return message;
    }
	
    public Message updateMessage(Message message) {
    	if(message.getId() == 0) {
    		return null;
    	}
    	messages.put(message.getId(), message);
    	return message;
    }
    
    public void removeMessage(int id) {
    	 messages.remove(id);
    	 System.out.println(messages);
    }
}
