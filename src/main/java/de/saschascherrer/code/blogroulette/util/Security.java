package de.saschascherrer.code.blogroulette.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

import de.saschascherrer.code.blogroulette.persistence.User;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

public class Security {

	public static String PRIVATE_KEY = "xUYbP3AoJ+JwyaKOjjKh67D3UaNkPst6EliortY3DxU=";

	public static String getHash(String passwordToHash, String salt) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	public static User validToken(HttpServletRequest request) {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (token.toLowerCase().contains("bearer")) {
			String[] tmp = token.split(" ");
			token = tmp[tmp.length - 1];
		}
		if (token == null || token.isEmpty())
			return null;
		try {
			JwtParser signed = Jwts.parser().setSigningKey(PRIVATE_KEY);
			String name = signed.parseClaimsJws(token).getBody().getSubject();
			Query query = EMM.getEm().createQuery("select u from User u where u.username like :name");
			query.setParameter("name", name);
			List<?> list = query.getResultList();
			if (list.size() < 1)
				return null;
			User u = (User) list.get(0);
			if (u.isTokenValid(token)) {
				return u;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
