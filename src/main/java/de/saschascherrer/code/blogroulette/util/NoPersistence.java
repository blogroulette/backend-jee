package de.saschascherrer.code.blogroulette.util;

import java.util.ArrayList;

import de.saschascherrer.code.blogroulette.persistence.Comment;
import de.saschascherrer.code.blogroulette.persistence.Message;
import de.saschascherrer.code.blogroulette.persistence.User;

/**
 * Dummy Class! Pretends to be a persistence implementation, but its not. For
 * test usage
 * 
 * @author felix
 *
 */
public class NoPersistence implements Persistence {

	Persistence me = null;

	ArrayList<Message> messages = new ArrayList<>();
	ArrayList<User> users = new ArrayList<>();
	ArrayList<Comment> comments = new ArrayList<>();

	@Override
	public Persistence getPersistence() {
		if (me == null)
			me = new NoPersistence();
		return me;
	}

	@Override
	public Message getMessage(long id) {
		for (Message m : messages)
			if (m.getId() == id)
				return m;
		return null;
	}

	@Override
	public Message getMessage() {
		if (messages.size() == 0)
			return null;
		return messages.get((int) (Math.random() * messages.size()));
	}

	@Override
	public Comment getComment(long id) {
		for (Comment c : comments)
			if (c.getId() == id)
				return c;
		return null;
	}

	@Override
	public User getUser(long id) {
		for (User u : users)
			if (u.getId() == id)
				return u;
		return null;
	}

	@Override
	public void addMessage(Message m) {
		messages.add(m);
	}

	@Override
	public void addUser(User u) {
		users.add(u);
	}

	@Override
	public void addComment(Comment c) {
		comments.add(c);
	}

}
