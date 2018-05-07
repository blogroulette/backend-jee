package de.saschascherrer.code.blogroulette.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommentTest {

    private Comment c = new Comment("Das ist ein Testkommentar!");
    @Test
    public void testAddVote() {
        c.addVote(1);
        assertEquals(1, c.getVotes());
        c.addVote(-2);
        assertEquals(-1, c.getVotes());
    }

}
