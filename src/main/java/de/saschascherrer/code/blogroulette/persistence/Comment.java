package de.saschascherrer.code.blogroulette.persistence;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

import de.saschascherrer.code.blogroulette.util.Sendable;

/**
 * @author felix
 */
public class Comment implements Sendable, Serializable {
    private static final long serialVersionUID = -8851603282710819730L;
    private int id;

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

    public Comment() {
    }

    public Comment(String text) {
        timestamp = new SimpleDateFormat("yyyy-MM-dd'T'h:m:ssZZZZZ").format(new Date());
        this.text = text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getJson() {
        StringJoiner sj = new StringJoiner(",", "{", "}");
        sj.add("\"commentid\":\"" + id + "\"");
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

    /**
     * @param i
     *            wenn positiv = Vote up, wenn negativ = Vote down
     */
    public void addVote(int i) {
        votes += i;
    }

    public int getVotes() {
        return votes;
    }

}
