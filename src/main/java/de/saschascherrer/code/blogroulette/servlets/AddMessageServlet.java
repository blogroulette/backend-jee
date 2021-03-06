package de.saschascherrer.code.blogroulette.servlets;

import java.io.IOException;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import de.saschascherrer.code.blogroulette.inputs.Input;
import de.saschascherrer.code.blogroulette.inputs.JsonMessage;
import de.saschascherrer.code.blogroulette.persistence.Message;
import de.saschascherrer.code.blogroulette.util.EMM;
import de.saschascherrer.code.blogroulette.util.Security;
import de.saschascherrer.code.blogroulette.util.Status;

/**
 * Servlet implementation class AddMessageServlet
 */
@Named
@RequestScoped
public class AddMessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Transactional
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean plan = true;
        if (Security.validToken(request) == null) {
            response.sendError(403, "Verboten!");
            plan = false;
            return;
        }
        EntityManager em = EMM.getEm();
        try {
            JsonMessage json = (JsonMessage) Input.umarshal(request, JsonMessage.class);
            String title = json.getTitle();
            String text = json.getText();
            if (!title.isEmpty() && !text.isEmpty()) {
                Message m = new Message(title, text);

                em.getTransaction().begin();
                em.persist(m);
                em.getTransaction().commit();
                em.close();

                new Status("ok").writeToOut(response);
            } else {
                new Status("error", "Konnte Text und Titel nicht finden").writeToOut(response);
                plan = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (plan)
                new Status("error", "Das Speichern der Mitteilung ist fehlgeschlagen")
                        .writeToOut(response);
        }
    }

}
