package de.saschascherrer.code.blogroulette.inputs;

public class JsonVoteComment {
	private String messageid;
	private String commentid;
	private String vote;

	public String getMessageid() {
		return messageid;
	}

	public boolean up() {
		return !vote.equals("down");
	}

	public int getCommentid() {
		try {
			return Integer.parseInt(commentid);
		} catch (Exception e) {
			return -1;
		}
	}
}
