package de.saschascherrer.code.blogroulette.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.saschascherrer.code.blogroulette.persistence.Message;
import de.saschascherrer.code.blogroulette.util.Status;

public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected EntityManager em = Persistence.createEntityManagerFactory("blogroulette").createEntityManager();

	public Message[] messageList() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("Select m " + "from Message m " + "ORDER BY m.id ASC");
		List<?> list = query.getResultList();

		return (Message[]) list.toArray(new Message[list.size()]);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			BufferedReader reader = new BufferedReader(req.getReader());
			String temp = null;
			String s = "";
			while ((temp = reader.readLine()) != null)
				s = temp;

			Message[] ms = messageList();
			Message m;
			if (s.contains("messageid"))
				m = ms[0];
			else
				m = ms[(int) (Math.random() * ms.length)];
			m.writeToOut(resp);
		} catch (Exception e) {
			e.printStackTrace();
			new Status("error", "Meldungen konnten nicht geladen werden").writeToOut(resp);
		}
	}
}
