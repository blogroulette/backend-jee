package de.saschascherrer.code.blogroulette.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.saschascherrer.code.blogroulette.inputs.Input;
import de.saschascherrer.code.blogroulette.inputs.JsonRegister;
import de.saschascherrer.code.blogroulette.persistence.User;
import de.saschascherrer.code.blogroulette.util.EMM;
import de.saschascherrer.code.blogroulette.util.Status;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			JsonRegister json = (JsonRegister) Input.umarshal(request, JsonRegister.class);
			User u = new User(json.getUser(), json.getPassword(), json.getSalt());
			EntityManager em = EMM.getEm();
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
			em.close();
			u.writeToOut(response);
		} catch (Exception e) {
			e.printStackTrace();
			new Status("error", "Das Erstellen des Nutzers ist fehlgeschlagen").writeToOut(response);
		}
	}

}
