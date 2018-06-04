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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import de.saschascherrer.code.blogroulette.util.Sendable;

/**
 * @author felix
 *
 */
@Entity
@Table(name = "MESSAGE")
public class Message implements Sendable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * RFC3339 Timestamp
	 */
	@Column(length = 128)
	private String timestamp;

	/**
	 * Messages title
	 */
	@Column(nullable = false, length = 140)
	private String title;

	/**
	 * Messages text
	 */
	@Column(columnDefinition = "TEXT")
	private String text;

	/**
	 * Messages votes
	 */
	@Column(length = 128)
	private int votes = 0;

	/**
	 * List of Comments correlating to the Message
	 */
	@Lob
	private Comment[] comments = new Comment[1];

	public Message() {
	}

	public Message(String title, String text) {
		timestamp = new SimpleDateFormat("yyyy-MM-dd'T'H:m:ssZZZZZ").format(new Date());
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
			if (c != null)
				cj.add(c.getJson());
		sj.add("\"comments\":" + cj.toString());
		return sj.toString();
	}

	public void addComment(Comment c) {
		ArrayList<Comment> temp = new ArrayList<>();
		for (Comment co : comments) {
			if (co != null)
				temp.add(co);
		}
		temp.add(c);
		c.setId(temp.size());
		comments = temp.toArray(new Comment[temp.size()]);
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

	/**
	 * 
	 * @param i wenn positiv = Vote up, wenn negativ = Vote down
	 */
	public void addVote(int i) {
		votes += i;
	}

	public Comment getComment(int index) {
		for (Comment c : comments) {
			if (c.getId() == index) {
				return c;
			}
		}
		return null;
	}
}
