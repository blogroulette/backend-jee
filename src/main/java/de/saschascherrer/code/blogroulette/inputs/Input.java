package de.saschascherrer.code.blogroulette.inputs;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

public class Input {

	public static JsonMessage umarshal(HttpServletRequest request, Class<JsonMessage> class1) throws IOException {
		Gson gson = new Gson();
		BufferedReader reader = request.getReader();
		JsonMessage ret=(JsonMessage) gson.fromJson(reader, class1);
		return ret;
	}
}
