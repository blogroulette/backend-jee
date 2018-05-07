package de.saschascherrer.code.blogroulette.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import de.saschascherrer.code.blogroulette.util.Security;
import de.saschascherrer.code.blogroulette.util.Sendable;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

/**
 * @author felix
 */
@Entity
@Table(name = "USER")
public class User implements Sendable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 128, unique = true)
    private String username;

    @Column(nullable = false)
    private String salt;

    @Column(columnDefinition = "TEXT")
    private String token;

    /**
     * Default Constructor
     */
    public User() {
    }

    public User(String username, String password, String salt) {
        super();
        this.password = password;
        this.username = username;
        this.salt = salt;
        token = generateJWTToken(username) + ";";
    }

    private String generateJWTToken(String user) {
        String token = Jwts.builder().setSubject(user).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.decode(Security.PRIVATE_KEY))
                .compact();
        return token;
    }

    public long getId() {
        return id;
    }

    private void addToken(String t) {
        token += t;
        token += ";";
        if (token.split(";").length > 5) {
            String[] tokens = token.split(";");
            String tmp = "";
            for (int i = 1; i < tokens.length; i++) {
                tmp += tokens[i] + ";";
            }
            token = tmp;
        }
    }

    public boolean login(String p) {
        String hash = Security.getHash(p, salt);
        boolean b = password.equals(hash);
        if (b) {
            addToken(generateJWTToken(username));
        }
        return b;
    }

    public String getUsername() {
        return username;
    }

    public boolean isTokenValid(String t) {
        return token.contains(t);
    }

    public void deleteTokens() {
        token = "";
    }

    public void newPassword(String password, String salt, String salted) {
        this.salt = salt;
        this.password = salted;
        login(password);
    }

    public void logout(String t) {
        token = token.replaceAll(t, "");
        token = token.replaceAll(";;", ";");
    }

    private String getLastToken() {
        String[] tokens = token.split(";");
        return tokens[tokens.length - 1];
    }
    
    public boolean tokensAvailable() {
        if (token.length()<5) {
            return false;
        }
        return true;
    }

    @Override
    public String getJson() {
        return "{\"username\": \"" + username + "\", \"token\": \"" + getLastToken() + "\"}";
    }
}
