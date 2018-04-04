package de.saschascherrer.code.blogroulette.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

import de.saschascherrer.code.blogroulette.inputs.Input;
import de.saschascherrer.code.blogroulette.inputs.JsonMessage;
import de.saschascherrer.code.blogroulette.persistence.Message;
import de.saschascherrer.code.blogroulette.util.Status;

/**
 * Servlet implementation class AddMessageServlet
 */
@Named
@RequestScoped
public class AddMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	protected EntityManager em;

	@Resource
	private UserTransaction userTransaction;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Transactional
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			JsonMessage json = Input.umarshal(request, JsonMessage.class);
			String title = json.getTitle();
			String text = json.getText();
			if (!title.isEmpty() && !text.isEmpty()) {
				Message m = new Message(title, text);
				userTransaction.begin();
				em.persist(m);
				userTransaction.commit();
				new Status("ok").writeToOut(response);
			}
			else {
				new Status("error", "Konnte Text und Titel nicht finden").writeToOut(response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			new Status("error", "Das Speichern der Mitteilung ist fehlgeschlagen").writeToOut(response);
		}
	}

}
