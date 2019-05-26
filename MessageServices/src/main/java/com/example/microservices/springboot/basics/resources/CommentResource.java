package com.example.microservices.springboot.basics.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.example.microservices.springboot.basics.models.Comments;
import com.example.microservices.springboot.basics.models.Message;
import com.example.microservices.springboot.basics.services.CommentService;
import com.example.microservices.springboot.basics.services.MessageService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {

	CommentService comment = new CommentService();

	@GET
	public List<Comments> getAllComments(@PathParam("messageId") int messageId) {
		return comment.getAllComments(messageId);
	}
	
	@POST
	public Comments addComment(Comments com, @PathParam("messageId") int messageId) {
		return comment.addComment(messageId, com);
	}
    
	@PUT
    @Path("/{commentId}")
    public Comments updateComment(Comments com, @PathParam("commentId") int id, @PathParam("messageId") int messageId) {
		com.setId(id);
		return comment.updateComment(messageId, com);
	}
	
	@DELETE
	@Path("/{commentId}")
	public Comments removeComment(@PathParam("commentId") int id, @PathParam("messageId") int messageId) {
		return comment.removeComment(messageId, id);
	}
}

