package com.example.microservices.springboot.basics.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.example.microservices.springboot.basics.database.DatabaseClass;
import com.example.microservices.springboot.basics.models.Comments;
import com.example.microservices.springboot.basics.models.Message;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	
	private Map<Integer,Message> messages = DatabaseClass.getMessages();
	
	public List<Comments> getAllComments(int messageId){
	     Map<Integer,Comments> comment =  messages.get(messageId).getComments();
	     return new ArrayList<Comments>(comment.values());
    }
   
   public Comments getComment(int messageId,int id){
   	return messages.get(messageId).getComments().get(id);
   }
   
   public Comments addComment(int messageId, Comments comment){
	Map<Integer,Comments> comments = messages.get(messageId).getComments();
	System.out.println(comments.size());
	comment.setId(comments.size() + 1);
   	comments.put(comment.getId(),comment);
   	return comment;
   }
	
   public Comments updateComment(int messageId, Comments comment) {
   	if(messages.get(messageId).getComments().isEmpty()) {
   		return null;
   	}
   	Map<Integer,Comments> comments = messages.get(messageId).getComments();
   	comments.put(comment.getId(), comment);
   	return comment;
   }
   
   public Comments removeComment(int messageId,int commentId) {
   	 return messages.get(messageId).getComments().remove(commentId);
   }
   
}
