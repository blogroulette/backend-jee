package de.saschascherrer.code.blogroulette.util;

import de.saschascherrer.code.blogroulette.persistence.Comment;
import de.saschascherrer.code.blogroulette.persistence.Message;
import de.saschascherrer.code.blogroulette.persistence.User;

public interface Persistence {
	
	/**
	 * @return a global Persistence impl
	 */
	Persistence getPersistence();
	
	/**
	 * gets a specific Message
	 * @param id ID of the Message
	 * @return Message
	 */
	Message getMessage(long id);
	
	/**
	 * gets a random Message
	 * @return Message
	 */
	Message getMessage();
	
	/**
	 * gets a specific Comment
	 * @param id ID of the Comment
	 * @return Comment
	 */
	Comment getComment(long id);
	
	/**
	 * gets a specific User
	 * @param id ID of the User
	 * @return User
	 */
	User getUser(long id);
	
	/**
	 * adds a Message
	 * @param m Message to add
	 */
	void addMessage(Message m);
	
	/**
	 * Adds a User
	 * @param u USer to add
	 */
	void addUser(User u);
	
	/**
	 * Adds a Comment
	 * @param c Comment to add
	 */
	void addComment(Comment c);
}
