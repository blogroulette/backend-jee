package de.saschascherrer.code.blogroulette.servlets;

import java.io.BufferedReader;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.saschascherrer.code.blogroulette.persistence.Message;
import de.saschascherrer.code.blogroulette.util.Status;

/**
 * Servlet implementation class AddMessageServlet
 */
public class AddMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected EntityManager em;

	public AddMessageServlet() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("blogroulette");
		em = emf.createEntityManager();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Message m = new Message("test", "text");
		em.getTransaction().begin();
		em.persist(m);
		em.getTransaction().commit();
		new Status(jb.toString()).writeToOut(response);
	}

}
