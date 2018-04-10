package de.saschascherrer.code.blogroulette.inputs;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

public class Input {

	public static Object umarshal(HttpServletRequest request, Class<?> class1) throws IOException {
		Gson gson = new Gson();
		request.setCharacterEncoding("utf8");
		BufferedReader reader = request.getReader();
		return gson.fromJson(reader, class1);
	}
}
