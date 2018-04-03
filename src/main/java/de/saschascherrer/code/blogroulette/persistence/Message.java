/**
 * 
 */
package de.saschascherrer.code.blogroulette.persistence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author felix
 *
 */
public class Message implements Sendable {
	private long id;

	/**
	 * RFC3339 Timestamp
	 */
	private String timestamp;

	/**
	 * Messages title
	 */
	private String title;

	/**
	 * Messages text
	 */
	private String text;

	/**
	 * Messages votes
	 */
	private int votes = 0;

	/**
	 * List of Comments correlating to the Message
	 */
	private ArrayList<Comment> comments=new ArrayList<>();

	public Message(String title, String text)
	{
		timestamp = new SimpleDateFormat("yyyy-MM-dd'T'h:m:ssZZZZZ").format(new Date());
		this.title = title;
		this.text = text;
	}

	public long getId() {
		return id;
	}

	@Override
	public String getJson() {
		StringBuilder sb=new StringBuilder();
		sb.append("{\n");
		sb.append("\"messageid\" : \""+id+"\",\n");
		sb.append("\"timestamp\" : \""+timestamp+"\",\n");
		sb.append("\"title\" : \""+title+"\",\n");
		sb.append("\"text\" : \""+text+"\",\n");
		sb.append("\"votes\" : \""+votes+"\",\n");
		sb.append("}");
		return sb.toString();
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getTitle() {
		return title;
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
