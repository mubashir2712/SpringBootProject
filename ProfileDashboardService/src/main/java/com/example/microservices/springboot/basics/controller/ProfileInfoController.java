package com.example.microservices.springboot.basics.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.microservices.springboot.basics.models.Comments;
import com.example.microservices.springboot.basics.models.Message;
import com.example.microservices.springboot.basics.models.ProfileInfo;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@CrossOrigin("*")
@RefreshScope
@RestController
@Component
public class ProfileInfoController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@Value("ProfileService")
	private String profileSearchServiceId;
	
	@Value("MessageServer")
	private String messageService;
	
    @RequestMapping("/dashboard/{myself}")
    //@ResponseStatus(value = HttpStatus.OK)
    public ProfileInfo findme(@PathVariable String myself) {
        Application application = eurekaClient.getApplication(profileSearchServiceId);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "profile/find/" + myself;
        System.out.println("URL" + url);
        ProfileInfo emp = restTemplate.getForObject(url, ProfileInfo.class);
        System.out.println("RESPONSE " + emp);
        return emp;
    }
    @RequestMapping("/dashboard/peers")
    public Collection < ProfileInfo > findPeers() {
        Application application = eurekaClient.getApplication(profileSearchServiceId);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "profile/findall";
        System.out.println("URL" + url);
        Collection < ProfileInfo > list = restTemplate.getForObject(url, Collection.class);
        System.out.println("RESPONSE " + list);
        return list;
    }
    @RequestMapping(value = "/dashboard/add", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addme(@RequestBody ProfileInfo profile) {
        Application application = eurekaClient.getApplication(profileSearchServiceId);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "profile/";
        System.out.println("URL" + url);
        //ProfileInfo emp = restTemplate.getForObject(url, ProfileInfo.class);
        System.out.println("InDashboard " + profile);
        ProfileInfo emp = restTemplate.postForObject(url, profile, ProfileInfo.class);
        System.out.println("RESPONSE My" + emp);
        //return emp;
    }
    
    @RequestMapping(value = "/dashboard/update/{profileId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateme(@RequestBody ProfileInfo profile, @PathVariable String profileId) {
        Application application = eurekaClient.getApplication(profileSearchServiceId);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "profile/" + "update/" + profileId;
        System.out.println("URL" + url);
        //System.out.println("profileId" + );
        //ProfileInfo emp = restTemplate.getForObject(url, ProfileInfo.class);
        profile.setProfileName(profileId);
        System.out.println("InDashboard " + profile);
        System.out.println("ProfileName " + profileId);
        restTemplate.put(url, profile);
        //System.out.println("RESPONSE My" + emp);
        //return emp;
    }
    
    @RequestMapping(value = "/dashboard/addFriend/{profileId}", method = RequestMethod.PUT)
    public void addAsFriend(@RequestBody ProfileInfo profile, @PathVariable String profileId) {
        Application application = eurekaClient.getApplication(profileSearchServiceId);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "profile/" + "addFriend/" + profileId;
        System.out.println("URL" + url);
        //ProfileInfo pro = new ProfileInfo();
        //pro.setProfileName(profileId);
      //profile.setProfiles(1,pro);
        System.out.println("InDashboard " + profile);
        System.out.println("ProfileName " + profileId);
        restTemplate.postForObject(url, profile, ProfileInfo.class);
    }
    
    @RequestMapping("/dashboard/getFriends/{myself}")
    public Collection < ProfileInfo > getAllFriends(@PathVariable String myself) {
        Application application = eurekaClient.getApplication(profileSearchServiceId);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "profile/findallFriends/" + myself;
        System.out.println("URL" + url);
        Collection < ProfileInfo > list = restTemplate.getForObject(url, Collection.class);
        System.out.println("RESPONSE " + list);
        return list;
    }
    
    @RequestMapping("/dashboard/messages")
    @ResponseStatus(value = HttpStatus.OK)
    public Collection<Message> getAllMessages(){
    	Application application = eurekaClient.getApplication(messageService);
    	InstanceInfo instanceInfo = application.getInstances().get(0);
    	String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "messages/";
        System.out.println("URL" + url);
        Collection <Message> list = restTemplate.getForObject(url, Collection.class);
        System.out.println("RESPONSE" + list);
        return list;
    }
    
    @RequestMapping("/dashboard/messages/{messageId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Message getMessage(@PathVariable Integer messageId) {
        Application application = eurekaClient.getApplication(messageService);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "messages/" + messageId;
        System.out.println("URL" + url);
        Message msg = restTemplate.getForObject(url, Message.class);
        System.out.println("RESPONSE " + msg);
        return msg;
    }
    @RequestMapping(value = "/dashboard/messages/add", method = RequestMethod.POST)
    public void addMessage(@RequestBody Message message) {
        Application application = eurekaClient.getApplication(messageService);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "messages/";
        System.out.println("URL" + url);
        Message msg = restTemplate.postForObject(url, message, Message.class);
        System.out.println("RESPONSE " + msg);
        //return msg;
    }
    @RequestMapping(value = "/dashboard/messages/update/{messageId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateMessage(@RequestBody Message message, @PathVariable Integer messageId) {
        Application application = eurekaClient.getApplication(messageService);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "messages/" + messageId;
        System.out.println("URL" + url);
        restTemplate.put(url, message);
        //System.out.println("RESPONSE " + msg);
        //return msg;
    }
    @RequestMapping(value = "/dashboard/messages/{messageId}/comments", method = RequestMethod.POST)
    public void addComment(@RequestBody Comments comment, @PathVariable Integer messageId) {
        Application application = eurekaClient.getApplication(messageService);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "messages/" + messageId + "/comments/";
        System.out.println("URL" + url);
        Comments comt = restTemplate.postForObject(url, comment, Comments.class);
        System.out.println("RESPONSE " + comt);
        //return comt;
    }
}
