package de.saschascherrer.code.blogroulette.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.saschascherrer.code.blogroulette.inputs.Input;
import de.saschascherrer.code.blogroulette.inputs.JsonComment;
import de.saschascherrer.code.blogroulette.persistence.Comment;
import de.saschascherrer.code.blogroulette.persistence.Message;
import de.saschascherrer.code.blogroulette.util.EMM;
import de.saschascherrer.code.blogroulette.util.Security;
import de.saschascherrer.code.blogroulette.util.Status;

/**
 * Servlet implementation class AddCommentServlet
 */
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(Security.validToken(request)==null) {
			response.sendError(403, "Verboten!");
			return;
		}
		try {
			JsonComment json = (JsonComment) Input.umarshal(request, JsonComment.class);
			Message m = EMM.getEm().find(Message.class,Long.parseLong(json.getMessageid()));
			m.addComment(new Comment(json.getText()));
			EntityManager em=EMM.getEm();
			em.getTransaction().begin();
			em.merge(m);
			em.getTransaction().commit();
			em.close();
			new Status("ok").writeToOut(response);
		} catch (Exception e) {
			e.printStackTrace();
			new Status("error", "Das Speichern des Kommentars ist fehlgeschlagen").writeToOut(response);
		}
	}

}
