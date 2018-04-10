package de.saschascherrer.code.blogroulette.util;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMM {

	private static EntityManagerFactory fact;
	private static Map<String, String> properties;
	static {
		properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/blogroulette");
		properties.put("javax.persistence.jdbc.driver", "org.mariadb.jdbc.Driver");
		properties.put("javax.persistence.schema-generation.database.action", "create");

		properties.put("javax.persistence.jdbc.user", "root");
		properties.put("javax.persistence.jdbc.password", "felix");
		fact = Persistence.createEntityManagerFactory("blogroulette", properties);
	}

	public static EntityManager getEm() {
		return fact.createEntityManager();
	}
}
