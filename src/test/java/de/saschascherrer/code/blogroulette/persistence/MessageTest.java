package de.saschascherrer.code.blogroulette.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MessageTest {

    private Message m = new Message("Test", "Das ist eine Test-Message");
    private Comment c = new Comment("Das ist aber ein toller Test");

    @Test
    public void testAddComment() {
        m.addComment(c);
        assertEquals("Das ist aber ein toller Test", m.getComment(1).getText());
        assertTrue(m.getJson().contains("Das ist aber ein toller Test"));
    }

    @Test
    public void testAddVote() {
        m.addVote(1);
        assertEquals(1, m.getVotes());
        m.addVote(-2);
        assertEquals(-1, m.getVotes());
    }

}
