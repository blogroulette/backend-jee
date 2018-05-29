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
        Map<String, String> environment = System.getenv();
        for (String envName : environment.keySet()) {
            System.out.printf("'%s'='%s' \n", envName, environment.get(envName));
        }

        properties = new HashMap<String, String>();

        if (environment.keySet().contains("BLOGROULETTE_DB_URL")) {
            properties.put("javax.persistence.jdbc.url", environment.get("BLOGROULETTE_DB_URL"));
        } else {
            properties.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/blogroulette");
        }

        if (environment.keySet().contains("BLOGROULETTE_DB_DRIVER")) {
            properties.put("javax.persistence.jdbc.driver", environment.get("BLOGROULETTE_DB_DRIVER"));
        } else {
            properties.put("javax.persistence.jdbc.driver", "org.mariadb.jdbc.Driver");
        }

        if (environment.keySet().contains("BLOGROULETTE_DB_USER")) {
            properties.put("javax.persistence.jdbc.user", environment.get("BLOGROULETTE_DB_USER"));
        } else {
            properties.put("javax.persistence.jdbc.user", "blogroulette");
        }

        if (environment.keySet().contains("BLOGROULETTE_DB_PASSWORD")) {
            properties.put("javax.persistence.jdbc.password", environment.get("BLOGROULETTE_DB_PASSWORD"));
        } else {
            properties.put("javax.persistence.jdbc.password", "blogroulettedefaultpassword");
        }

        properties.put("javax.persistence.schema-generation.database.action", "create");
        fact = Persistence.createEntityManagerFactory("blogroulette", properties);
    }

    public static EntityManager getEm() {
        return fact.createEntityManager();
    }
}
