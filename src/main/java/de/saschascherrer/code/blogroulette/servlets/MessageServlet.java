package de.saschascherrer.code.blogroulette.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.saschascherrer.code.blogroulette.persistence.Comment;
import de.saschascherrer.code.blogroulette.persistence.Message;

public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Message m=new Message("Lorem Ipsum", "Dolor sit amet");
		m.addComment(new Comment("Dies ist ein Blindtext"));
		m.addComment(new Comment("Ein weiterer Blindtext"));
		m.writeToOut(resp);
	}
}
