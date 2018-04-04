package de.saschascherrer.code.blogroulette.servlets;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.saschascherrer.code.blogroulette.persistence.Message;
import de.saschascherrer.code.blogroulette.util.Status;

public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	protected EntityManager em;
	
	public List<Message> findAllMessages() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Message> q = cb.createQuery(Message.class);
		Root<Message> root = q.from(Message.class);
		q.select(root);
		q.orderBy(cb.desc(root.get("id")));
		TypedQuery<Message> query = em.createQuery(q);
		List<Message> results = query.getResultList();
		return results;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		List<Message> ms=findAllMessages();
		Message m=ms.get((int)(Math.random()*ms.size()));
		m.writeToOut(resp);
		}catch(Exception e) {
			e.printStackTrace();
			new Status("error", "Meldungen konnten nicht geladen werden").writeToOut(resp);
		}
	}
}
