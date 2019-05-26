package com.example.microservices.springboot.basics.config;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ApplicationPath;

import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;

@ApplicationPath("/")
public class DemoResourcesConfig extends PackagesResourceConfig {

  private static final Map<String, Object> properties() {
    Map<String, Object> result = new HashMap<>();
    result.put(PackagesResourceConfig.PROPERTY_PACKAGES, "com.sun.jersey;com.example.microservices.springboot.basics.resources");
    // To forward non-Jersey paths to servlet container for Spring Boot actuator endpoints to work.
    result.put("com.sun.jersey.config.feature.FilterForwardOn404", "true");
    result.put(JSONConfiguration.FEATURE_POJO_MAPPING, "true");
    return result;
  }

  public DemoResourcesConfig() {
    super(properties());
  }
}