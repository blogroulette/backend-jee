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
 * 
 * @author felix
 *
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

	@Column
	private String token;
	
	/**
	 * Default Constructor
	 */
	public User() {}

	public User(String username, String password, String salt) {
		super();
		this.password = password;
		this.username = username;
		this.salt = salt;
		this.token = generateJWTToken(username);
	}

	private String generateJWTToken(String user) {
		String token = Jwts.builder().setSubject(user).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,
				TextCodec.BASE64.decode("xUYbP3AoJ+JwyaKOjjKh67D3UaNkPst6EliortY3DxU=")).compact();
		return token;
	}

	public long getId() {
		return id;
	}

	public boolean login(String p) {
		String hash = Security.getHash(p, salt);
		boolean b = password.equals(hash);
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
