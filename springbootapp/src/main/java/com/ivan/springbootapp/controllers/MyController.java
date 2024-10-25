package com.ivan.springbootapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ivan.springbootapp.services.MyService;

@Component
public class MyController {
  @Autowired
  private MyService myService;

  public void execute() {
    myService.performAction();
  }
}
