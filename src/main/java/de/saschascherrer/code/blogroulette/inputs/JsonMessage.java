package de.saschascherrer.code.blogroulette.inputs;

public class JsonMessage extends Unmarshable{
	private String title;
	private String text;
	public String getTitle() {
		return title;
	}
	public String getText() {
		return text;
	}
}
