package de.saschascherrer.code.blogroulette.persistence;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * For classes which a capable to spit out Json
 * @author felix
 *
 */
public interface Sendable {
	
	/**
	 * Converts an Object to a Custom Json Object
	 * Must not return null
	 * @return Json
	 */
	String getJson();
	
	/**
	 * Writes Json to a ServletResponse
	 * @param resp Response to Write to
	 * @throws IOException 
	 */
	default void writeToOut(HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		out.print(getJson());
		out.flush();
	}
}
