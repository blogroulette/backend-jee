package de.saschascherrer.code.blogroulette.util;

public class Status implements Sendable {
	
	private String status;
	private String error="";

	public Status(String status) {
		this.status = status;
	}
	
	public Status(String status, String error) {
		this.status = status;
		this.error=error;
	}

	@Override
	public String getJson() {
		if(error.isEmpty())
		return "{\"status\":\""+status+"\"}";
		return "{\"status\":\""+status+"\",\"error\":\""+error+"\"}";
	}

}
