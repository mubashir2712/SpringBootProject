package com.example.microservices.springboot.basics.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.microservices.springboot.basics.models.Profile;

public class DatabaseClass {
	
	
	private static Map<String,Profile> profiles = new HashMap<String,Profile>();
	
	
	public static Map<String,Profile> getProfiles(){
		if(profiles.isEmpty()) {
			profiles.put("Shamik", new Profile("999999999",1,"Shamik Mitra","Shamik","sha@gmail.com","1234"));
			profiles.put("Samir", new Profile("8888888888",2,"Samir Mitra","Samir","samir@gmail.com","1234"));
			profiles.put("Swastika", new Profile("77777777",3,"Swastika Mitra","Swastika","swastika@gmail.com","4321"));
		}
		return profiles; 
	}
}