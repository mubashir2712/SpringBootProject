package com.example.microservices.springboot.basics.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.microservices.springboot.basics.models.Profile;
import com.example.microservices.springboot.basics.services.ProfileService;;
@RefreshScope
@RestController
@Component
public class ProfileSearchController {
   @Autowired
   ProfileService profileService;
   @RequestMapping("/profile/find/{id}")
   public Profile findById(@PathVariable String id){
      return profileService.findById(id);
   }
   @RequestMapping("/profile/findall")
   public Collection<Profile> findAll(){
      return profileService.findAll();
   }
   @RequestMapping("/profile/update/{profileId}")
   public Profile update(@RequestBody Profile profile, @PathVariable String profileId){
      return profileService.update(profileId,profile);
   }
   @RequestMapping(value = "/profile",method = RequestMethod.POST,
		          headers = "Accept=application/json")
   public Profile add(@RequestBody Profile profile){
      
	   System.out.println("8083 " + profile);
	   
	   System.out.println(profile.getPhoneNo()+ " " + profile.getId() + " "+
    		                profile.getProfileName()+ " " +profile.getFirstName() + " " +
    		                profile.getEmailId() + " " +profile.getPassword());
	   
	   
	   return ProfileService.createProfile(profile.getPhoneNo(),profile.getId(),
    		                profile.getProfileName(),profile.getFirstName(),
    		                profile.getEmailId(),profile.getPassword());
   }
   
   @RequestMapping("profile/addFriend/{profileId}")
   public Profile addFriends(@RequestBody Profile profile, @PathVariable String profileId){   
	      return profileService.updateFriends(profileId,profile);  
   }
}

