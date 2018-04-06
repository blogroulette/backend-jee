package de.saschascherrer.code.blogroulette.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;

import de.saschascherrer.code.blogroulette.persistence.User;
import de.saschascherrer.code.blogroulette.util.EMM;
import de.saschascherrer.code.blogroulette.util.Security;
import de.saschascherrer.code.blogroulette.util.Status;

/**
 * Servlet implementation class LoginServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			User u = Security.validToken(request);
			if (u == null) {
				new Status("error", "Nicht eingeloggt").writeToOut(response);
				return;
			}
			u.logout(request.getHeader(HttpHeaders.AUTHORIZATION));

			EntityManager em = EMM.getEm();
			em.getTransaction().begin();
			em.merge(u);
			em.getTransaction().commit();
			new Status("ok").writeToOut(response);
		} catch (Exception e) {
			e.printStackTrace();
			new Status("error", "Das Ausloggen ist fehlgeschlagen").writeToOut(response);
		}
	}

}
