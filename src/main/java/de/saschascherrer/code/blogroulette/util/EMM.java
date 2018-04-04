package de.saschascherrer.code.blogroulette.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMM {
	
	private static EntityManagerFactory fact=Persistence.createEntityManagerFactory("blogroulette");
	public static EntityManager getEm() {
		return fact.createEntityManager();
	}
}
