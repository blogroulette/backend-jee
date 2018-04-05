package de.saschascherrer.code.blogroulette.inputs;

import java.security.SecureRandom;

import de.saschascherrer.code.blogroulette.util.Security;

public class JsonRegister {
	String username;
	String password;
	String salt = "" + SecureRandom.getSeed(16);

	public String getUser() {
		return username;
	}

	public String getPassword() {
		return Security.getHash(password, salt);
	}

	public String getSalt() {
		return salt;
	}
}
