package de.saschascherrer.code.blogroulette.persistence;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

/**
 * 
 * @author felix
 *
 */
public class Comment implements Sendable{
	private long id;
	
	/**
	 * RFC3339 Timestamp
	 */
	private String timestamp;

	/**
	 * Comments text
	 */
	private String text;
	
	/**
	 * Comments votes
	 */
	private int votes = 0;

	public long getId() {
		return id;
	}
	
	public Comment(String text)
	{
		timestamp = new SimpleDateFormat("yyyy-MM-dd'T'h:m:ssZZZZZ").format(new Date());
		this.text = text;
	}
	
	@Override
	public String getJson() {
		StringJoiner sj = new StringJoiner(",", "{", "}");
		sj.add("\"messageid\":\"" + id + "\"");
		sj.add("\"timestamp\":\"" + timestamp + "\"");
		sj.add("\"text\":\"" + text + "\"");
		sj.add("\"votes\":\"" + votes + "\"");
		return sj.toString();
	}
	
	public String getTimestamp() {
		return timestamp;
	}

	public String getText() {
		return text;
	}

	public int getVotes() {
		return votes;
	}

	public void voteUp() {
		votes++;
	}

	public void voteDown() {
		votes--;
	}
}
