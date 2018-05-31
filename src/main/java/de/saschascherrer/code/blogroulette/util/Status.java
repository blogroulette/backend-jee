package de.saschascherrer.code.blogroulette.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Status implements Sendable {

    private String status;
    private String error = "";

    public Status(String status) {
        this.status = status;
    }

    public Status(String status, String error) {
        this.status = status;
        this.error = error;
    }

    @Override
    public String getJson() {
        if (error.isEmpty())
            return "{\"status\":\"" + status + "\"}";
        return "{\"status\":\"" + status + "\",\"error\":\"" + error + "\"}";
    }

    public void writeToOut(HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        if (!error.isEmpty())
            resp.sendError(400, error);
        else {
            out.print(getJson());
        }
        out.flush();
    }

}
