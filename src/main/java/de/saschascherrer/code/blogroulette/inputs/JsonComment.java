package de.saschascherrer.code.blogroulette.inputs;

import org.apache.commons.lang.StringEscapeUtils;

public class JsonComment {
	private String messageid;
	private String text;
	public String getMessageid() {
		return StringEscapeUtils.escapeJava(messageid);
	}
	public String getText() {
		System.out.println(text);
		System.out.println(StringEscapeUtils.escapeJava(text));
		return StringEscapeUtils.escapeJava(text);
	}
}
