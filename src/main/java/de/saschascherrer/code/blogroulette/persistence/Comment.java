package de.saschascherrer.code.blogroulette.persistence;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import de.saschascherrer.code.blogroulette.util.Sendable;

/**
 * 
 * @author felix
 *
 */
@Entity
@Table(name = "Comment")
public class Comment implements Sendable {
	@Id
	@Column(name = "id")
	private long id;

	/**
	 * RFC3339 Timestamp
	 */
	@Column(name = "timestamp")
	private String timestamp;

	/**
	 * Comments text
	 */
	@Column(name = "text")
	private String text;

	/**
	 * Comments votes
	 */
	@Column(name = "votes")
	private int votes = 0;

	public long getId() {
		return id;
	}

	public Comment(String text) {
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
