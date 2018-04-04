package de.saschascherrer.code.blogroulette.inputs;

public class JsonVoteMessage {
	private String messageid;
	private String vote;

	public String getMessageid() {
		return messageid;
	}
	
	public boolean up(){
		return !vote.equals("down");
	}
}
