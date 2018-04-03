/**
 * 
 */
package de.saschascherrer.code.blogroulette.persistence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import de.saschascherrer.code.blogroulette.util.Sendable;

/**
 * @author felix
 *
 */
@Entity
@Table(name = "Message")
public class Message implements Sendable {
	@Id
	@Column(name = "id")
	private long id;

	/**
	 * RFC3339 Timestamp
	 */
	@Column(name = "timestamp")
	private String timestamp;

	/**
	 * Messages title
	 */
	@Column(name = "title")
	private String title;

	/**
	 * Messages text
	 */
	@Column(name = "text")
	private String text;

	/**
	 * Messages votes
	 */
	@Column(name = "votes")
	private int votes = 0;

	/**
	 * List of Comments correlating to the Message
	 */
	@Column(name = "comments")
	private ArrayList<Comment> comments = new ArrayList<>();

	public Message(String title, String text) {
		timestamp = new SimpleDateFormat("yyyy-MM-dd'T'h:m:ssZZZZZ").format(new Date());
		this.title = title;
		this.text = text;
	}

	public long getId() {
		return id;
	}

	@Override
	public String getJson() {
		StringJoiner sj = new StringJoiner(",", "{", "}");
		sj.add("\"messageid\":\"" + id + "\"");
		sj.add("\"timestamp\":\"" + timestamp + "\"");
		sj.add("\"title\":\"" + title + "\"");
		sj.add("\"text\":\"" + text + "\"");
		sj.add("\"votes\":\"" + votes + "\"");
		StringJoiner cj = new StringJoiner(",", "[", "]");
		for (Comment c : comments)
			cj.add(c.getJson());
		sj.add("\"comments\":" + cj.toString());
		return sj.toString();
	}

	public void addComment(Comment c) {
		comments.add(c);
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
