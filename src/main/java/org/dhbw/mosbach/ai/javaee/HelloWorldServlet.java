package org.dhbw.mosbach.ai.javaee;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		final StringBuilder sb = new StringBuilder();

		req.getParameterMap().forEach(
				(key, values) -> sb.append(
						String.format("<strong>%s</strong>: %s<br/>\n", key,
								Stream.of(values).collect(Collectors.joining(", ")))));

		final String parameter = req.getParameter("myparam");

		final String path = req.getPathInfo();

		resp.getWriter().append(
				String.format(
						"<html><head><title>Servlet Hello World</title></head><body>Hello world (Path: %s)! <br/>myparam value is: %s. <p>Whole param list:<br/>%s</p></body></html>",
						path, parameter, sb.toString()));
	}
}
