package com.example.microservices.springboot.basics.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import com.example.microservices.springboot.basics.models.Message;
import com.example.microservices.springboot.basics.services.MessageService;

@Component
@Path("/messages")
public class MessageRecources {

	MessageService msg = new MessageService();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@QueryParam("year") int year,
			                         @QueryParam("start") int start,
			                         @QueryParam("size") int size) {
		if(year > 0) return msg.getAllMessagesForYear(year);
		if(start >= 0 && size > 0) {
			return msg.getAllPaginatedMessages(start, size);
		}
		return msg.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") int id) {
		return msg.getMessage(id); 
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessages(Message message) {
		return msg.addMessage(message);
	}
	
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateMessage(@PathParam("messageId") int id, Message message) {
		message.setId(id);
		msg.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void removeMessages(@PathParam("messageId") int id) {
		msg.removeMessage(id);
	}
	
	@Path("/{messageId}/comments")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}
