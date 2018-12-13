package com.springbootkubernetes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class HelloController {

  @GetMapping("/greetings")
  public String hello() {
    return makeAGetCall("http://demo-backend:8080/hello");
  }

  @GetMapping(value = "/capital/{country}")
  public String findCapital(@PathVariable("country") String country) {
    return makeAGetCall("http://demo-backend:8080/capital/" + country);
  }

  private String makeAGetCall(String url) {
    HttpURLConnection connection = null;
    BufferedReader reader = null;
    String retVal = null;
    try {
      URL resetEndpoint = new URL(url);
      connection = (HttpURLConnection) resetEndpoint.openConnection();
      //Set request method to GET as required from the API
      connection.setRequestMethod("GET");

      // Read the response
      reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      StringBuilder jsonSb = new StringBuilder();
      String line = null;
      while ((line = reader.readLine()) != null) {
        jsonSb.append(line);
      }
      retVal = jsonSb.toString();

      // print out the json response
      System.out.println(retVal);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // Clean up
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (connection != null) {
        connection.disconnect();
      }
    }
    return retVal;
  }

}
