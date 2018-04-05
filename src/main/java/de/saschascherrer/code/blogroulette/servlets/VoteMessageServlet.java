package de.saschascherrer.code.blogroulette.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.saschascherrer.code.blogroulette.inputs.Input;
import de.saschascherrer.code.blogroulette.inputs.JsonVoteMessage;
import de.saschascherrer.code.blogroulette.persistence.Message;
import de.saschascherrer.code.blogroulette.util.EMM;
import de.saschascherrer.code.blogroulette.util.Status;

/**
 * Servlet implementation class VoteMessageServlet
 */
public class VoteMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			JsonVoteMessage json = (JsonVoteMessage) Input.umarshal(request, JsonVoteMessage.class);
			Message m = EMM.getEm().find(Message.class, Long.parseLong(json.getMessageid()));
			m.addVote(json.upOrDown());
			EntityManager em = EMM.getEm();
			em.getTransaction().begin();
			em.merge(m);
			em.getTransaction().commit();
			new Status("ok").writeToOut(response);
		} catch (Exception e) {
			e.printStackTrace();
			new Status("error", "Das Speichern der Nachricht ist fehlgeschlagen").writeToOut(response);
		}
	}

}
