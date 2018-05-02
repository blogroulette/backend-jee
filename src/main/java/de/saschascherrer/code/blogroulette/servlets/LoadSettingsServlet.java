package de.saschascherrer.code.blogroulette.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.saschascherrer.code.blogroulette.persistence.User;
import de.saschascherrer.code.blogroulette.util.Security;
import de.saschascherrer.code.blogroulette.util.Status;

/**
 * Servlet implementation class VoteMessageServlet
 */
public class LoadSettingsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = Security.validToken(request);
        if (u == null) {
            response.sendError(403, "Verboten!");
            return;
        }
        try {
            u.writeToOut(response);
        } catch (Exception e) {
            e.printStackTrace();
            new Status("error", "Das Laden ist fehlgeschlagen")
                    .writeToOut(response);
        }
    }

}
