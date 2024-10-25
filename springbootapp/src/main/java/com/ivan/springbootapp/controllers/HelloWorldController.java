package com.ivan.springbootapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  @GetMapping("/hello")
  public String helloWorld() {
    return "helloworld";
  }

  @GetMapping("/error")
  public String error() {
    return "error";
  }
}
