package de.saschascherrer.code.blogroulette.servlets;

import java.io.IOException;
import java.util.List;

import javax.persistence.Query;
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
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private User user(String name) {
		Query query = EMM.getEm().createQuery("select u from User u where u.name like :name");
		query.setParameter("name", name);
		List<?> list = query.getResultList();
		if (list.size() < 1)
			return null;
		return (User) list.get(0);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			JsonRegister json = (JsonRegister) Input.umarshal(request, JsonRegister.class);
			User u = user(json.getUser());
			if (u == null) {
				new Status("error", "Der User existiert nicht").writeToOut(response);
				return;
			}
			if (!u.login(json.getPassword())) {
				new Status("error", "Anmeldeinformationen stimmen nicht überein").writeToOut(response);
				return;
			}
			u.writeToOut(response);
		} catch (Exception e) {
			e.printStackTrace();
			new Status("error", "Das Einloggen ist fehlgeschlagen").writeToOut(response);
		}
	}

}