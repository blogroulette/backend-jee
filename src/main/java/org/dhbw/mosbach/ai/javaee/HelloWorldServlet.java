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
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		final StringBuilder sb = new StringBuilder();

		req.getParameterMap().forEach(
				(key, values) -> sb.append(
						String.format("<strong>%s</strong>: %s<br/>\n", key,
								Stream.of(values).collect(Collectors.joining(", ")))));

		final String parameter = req.getParameter("myparam");

		resp.getWriter().append(
				String.format(
						"<html><head><title>Servlet Hello World</title></head><body>Hello world! <br/>myparam value is: %s. <p>Whole param list:<br/>%s</p></body></html>",
						parameter, sb.toString()));
	}
}
