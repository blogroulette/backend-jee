package de.saschascherrer.code.blogroulette.inputs;

public class JsonVoteComment {
	private String messageid;
	private String commentid;
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

	public int getCommentid() {
		try {
			return Integer.parseInt(commentid);
		} catch (Exception e) {
			return -1;
		}
	}
}
