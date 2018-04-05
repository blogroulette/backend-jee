package de.saschascherrer.code.blogroulette.inputs;

public class JsonVoteMessage {
	private String messageid;
	private String vote;

	public String getMessageid() {
		return messageid;
	}

	public int upOrDown() {
		if (vote.equals("down"))
			return -1;
		if (vote.equals("up"))
			return 1;
		return 0;
	}
}
