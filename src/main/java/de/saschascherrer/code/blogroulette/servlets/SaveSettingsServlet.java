package de.saschascherrer.code.blogroulette.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.saschascherrer.code.blogroulette.inputs.Input;
import de.saschascherrer.code.blogroulette.inputs.JsonSaveSettings;
import de.saschascherrer.code.blogroulette.persistence.User;
import de.saschascherrer.code.blogroulette.util.EMM;
import de.saschascherrer.code.blogroulette.util.Security;
import de.saschascherrer.code.blogroulette.util.Status;

/**
 * Servlet implementation class VoteMessageServlet
 */
public class SaveSettingsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = Security.validToken(request);
        boolean plan = true;
        if (u == null) {
            plan = false;
            response.sendError(403, "Verboten!");
            return;
        }
        try {
            JsonSaveSettings json = (JsonSaveSettings) Input.umarshal(request,
                    JsonSaveSettings.class);
            if (!u.getUsername().equals(json.getUser()) || !u.login(json.getPassword())) {
                plan=false;
                new Status("error", "Die Anmeldeinformationen stimmen nicht überein")
                        .writeToOut(response);
                return;
            }
            u.newPassword(json.getUnsaltedNewPassword(), json.getSalt(), json.getNewPassword());
            EntityManager em = EMM.getEm();
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            em.close();
            u.writeToOut(response);
        } catch (Exception e) {
            e.printStackTrace();
            if (plan)
                new Status("error", "Das Speichern ist fehlgeschlagen").writeToOut(response);
        }
    }

}
