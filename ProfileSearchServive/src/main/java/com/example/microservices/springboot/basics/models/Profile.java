package com.example.microservices.springboot.basics.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@XmlRootElement
public class Profile {

	private int id;
	private String profileName;
	private String firstName;
	private String emailId;
	private String password;
	private String phoneNo;
	private Date created;
	private Map<Integer,Profile> profiles = new HashMap<Integer,Profile>();	
	
	public Profile() {
		
	}
	public Profile(String phoneNo,int id, String profileName, String firstName, String emailId, String password) {
		this.id = id;
		this.profileName = profileName;
		this.firstName = firstName;
		this.emailId = emailId;
		this.password = password;
		this.phoneNo = phoneNo;
		this.created = new Date();
	}

	public Map<Integer, Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(Map<Integer, Profile> profiles) {
		this.profiles = profiles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", profileName=" + profileName + ", firstName=" + firstName + ", emailId="
				+ emailId + ", password=" + password + ", phoneNo=" + phoneNo + ", created=" + created + "]";
	}
	
}
