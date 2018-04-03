package de.saschascherrer.code.blogroulette.util;

public class Status implements Sendable {
	
	private String status;

	public Status(String status) {
		this.status = status;
	}

	@Override
	public String getJson() {
		return "{\"status\":\""+status+"\"}";
	}

}
