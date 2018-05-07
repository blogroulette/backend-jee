package de.saschascherrer.code.blogroulette.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.saschascherrer.code.blogroulette.util.Security;

public class UserTest {

    private String pwHash;
    private String pw;
    private String uname;
    private User user;
    
    
    private void createUser() {
    pwHash = Security.getHash("TestPasswort", "TestSalt");
    pw = "TestPasswort";
    uname = "TestUser";
    user = new User(uname, pwHash, "TestSalt");
    }
    
    @Test
    public void testLogin() {
        createUser();
        assertTrue(user.login(pw));
        assertTrue(!user.login("FalschesPasswort"));
    }
    
    @Test
    public void testIsTokenValid() {
        createUser();
        String token = user.getJson().replaceAll("[\"{,}:]*", "").replace("username TestUser token", "").trim();
        assertTrue(user.isTokenValid(token));
        assertTrue(!user.isTokenValid(token + "A"));
    }
    
    @Test
    public void testDeleteTokens() {
        createUser();
        user.deleteTokens();
        assertEquals("{\"\"}", user.getJson().replaceAll("\"username\": \"TestUser\", \"token\": ", ""));
    }
    
    @Test
    public void testNewPassword() {
        createUser();
        String newPW = "NeuesPasswort";
        String newSalt = "NeuesSalz";
        user.newPassword(newPW, newSalt, Security.getHash(newPW, newSalt));
        assertTrue(user.login(newPW));
        assertTrue(!user.login(pw));
    }
    
    @Test
    public void testLogout() {
        createUser();
        String token = user.getJson().replaceAll("[\"{,}:]*", "").replace("username TestUser token", "").trim();
        assertTrue(user.isTokenValid(token));
        user.logout(token);
        assertTrue(!user.tokensAvailable());
    }    
}
