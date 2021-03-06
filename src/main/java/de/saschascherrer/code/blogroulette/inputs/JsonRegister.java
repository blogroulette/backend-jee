package de.saschascherrer.code.blogroulette.inputs;

import java.security.SecureRandom;

import org.apache.commons.lang.StringEscapeUtils;

import de.saschascherrer.code.blogroulette.util.Security;

public class JsonRegister {
	String username;
	String password;
	String salt = "" + SecureRandom.getSeed(16);

	public String getUser() {
		return StringEscapeUtils.escapeJava(username);
	}

	public String getPassword() {
		return Security.getHash(getUnsaltedPassword(), salt);
	}
	
	public String getUnsaltedPassword() {
		return StringEscapeUtils.escapeJava(password);
	}

	public String getSalt() {
		return salt;
	}
}
