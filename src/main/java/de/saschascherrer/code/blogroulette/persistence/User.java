package de.saschascherrer.code.blogroulette.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import de.saschascherrer.code.blogroulette.util.Security;
import de.saschascherrer.code.blogroulette.util.Sendable;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * @author felix
 *
 */
@Entity
@Table(name = "USER")
public class User implements Sendable {
	@Id
	private long id;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, length = 128, unique = true)
	private String username;

	@Column(nullable = false)
	private String salt;

	@Column
	private String token;

	public User(String username, String password, String salt) {
		super();
		this.password = password;
		this.username = username;
		this.salt = salt;
		this.token = generateJWTToken(username);
	}

	private String generateJWTToken(String user) {
		String token = Jwts.builder().setSubject(user).claim("iat", System.currentTimeMillis())
				.signWith(SignatureAlgorithm.HS512, System.getProperty("JWT-KEY")).compact();
		return token;
	}

	public long getId() {
		return id;
	}

	public boolean login(String password) {
		String hash = Security.getHash(password, salt);
		boolean b = this.password.equals(hash);
		if (b) {
			this.token = generateJWTToken(username);
		}
		return b;
	}

	public String getUsername() {
		return username;
	}

	public void logout() {
		token = "";
	}

	@Override
	public String getJson() {
		return "{\"username\": " + username + ", token: " + token + "}";
	}
}
