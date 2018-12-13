package com.springbootkubernetes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    return "Demo Backend :)";
  }

  @GetMapping(value = "/capital/{country}")
  public String getCapital(@PathVariable("country") String country) {

    Map<String, String> names = new HashMap<>();
    names.put("armenia", "Yerevan");
    names.put("georgia", "Tbilisi");
    names.put("germany", "Berlin");
    names.put("usa", "Washington");
    names.put("france", "Paris");
    names.put("Italy", "Rome");

    String
        capital =
        names.get(country.toLowerCase()) == null ? "unknown" : names.get(country.toLowerCase());

    return "The capital of " + country + " is " + capital;
  }

}
