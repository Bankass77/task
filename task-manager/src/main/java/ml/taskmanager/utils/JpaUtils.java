package ml.taskmanager.utils;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtils {
	
	public static EntityManagerFactory getEntityManagerFactory(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword) {
		Map<String, String> propereties= new HashMap<String, String>();
		
		propereties.put("javax.persistence.jdbc.url","jdbc:mysql://"+ dbHost + ":"+ dbPort + "/"+ dbName);
		propereties.put("javax.persistence.jdbc.use", dbUser);
		propereties.put("javax.persistence.jdbc.password", dbPassword);
		return Persistence.createEntityManagerFactory("task-persistence-unit", propereties);
		
	}

}
