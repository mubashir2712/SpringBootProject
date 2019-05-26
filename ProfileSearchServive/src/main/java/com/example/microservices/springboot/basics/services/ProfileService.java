package com.example.microservices.springboot.basics.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.microservices.springboot.basics.database.DatabaseClass;
import com.example.microservices.springboot.basics.models.Profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ProfileService{

	 private static Map < String, Profile > ProfileRepsitory = DatabaseClass.getProfiles();
	
/*     public ProfileService {
        Stream < String > profileStream = Stream.of("999999999,1,Shamik Mitra,Shamik,sha@gmail.com,1234", "8888888888,2,Samir Mitra,Samir,samir@gmail.com,1234",
            "77777777,3,Swastika Mitra,Swastika,swastika@gmail.com,4321");

        ProfileRepsitory = profileStream.map(profileStr -> {
            String[] info = profileStr.split(",");
            return createProfile(info[0],new Integer(info[1]), info[2], info[3], info[4], info[5]);
        }).collect(Collectors.toMap(Profile::getId, pro -> pro));
    }
*/	

    public static Profile createProfile(String phoneNo,int id, String profileName, String firstName, String emailId, String password) {
    	Profile pro = new Profile();
        pro.setId(id);
        pro.setPhoneNo(phoneNo);
        pro.setProfileName(profileName);
        pro.setFirstName(firstName);
        pro.setEmailId(emailId);
        pro.setPassword(password);
        //return pro;
    	
    	pro.setId(ProfileRepsitory.size() + 1);
    	ProfileRepsitory.put(pro.getProfileName(),pro);
    	return pro;
    }
    public Profile findById(String id) {
        return ProfileRepsitory.get(id);
    }
    public Collection < Profile > findAll() {
        return ProfileRepsitory.values();
    }
    public Profile update(String profileId, Profile profile) {
    	if(profileId == null) {
    		return null;
    	}
    	ProfileRepsitory.put(profile.getProfileName(), profile);
    	return profile;
    }
    public Profile updateFriends(String profileId, Profile profile) {
    	if(profileId == null) {
    		return null;
    	}
    	Map<Integer,Profile> profileMap = ProfileRepsitory.get(profileId).getProfiles();
    	//profile.setId(profileMap.size() + 1);
    	profileMap.put(profileMap.size() + 1, profile);
    	
    	//ProfileRepsitory.put(profileId, profile);
    	
    	profileMap = ProfileRepsitory.get(profile.getProfileName()).getProfiles();
    	Profile pro = new Profile();
    	pro.setId(profileMap.size() + 1);
    	pro.setProfileName(profileId);
    	profileMap.put(profileMap.size() + 1, pro);
    	return profile;
    }
}
