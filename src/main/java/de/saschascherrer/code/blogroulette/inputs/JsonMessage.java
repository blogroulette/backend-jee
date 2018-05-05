package de.saschascherrer.code.blogroulette.inputs;

import org.apache.commons.lang.StringEscapeUtils;

public class JsonMessage {
	private String title;
	private String text;

	public String getTitle() {
		return StringEscapeUtils.escapeJava(title);
	}

	public String getText() {
		return StringEscapeUtils.escapeJava(text);
	}
}
