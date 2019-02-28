package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloWorld {

  public HelloWorld() {

  }

  @GetMapping("/greeting")
  public String sayHello(@RequestParam(value = "name") String name, Model model) {
    model.addAttribute("name",name);
    return "greeting";
  }

  @RequestMapping("/")
  public String noContext() {
    return "Hello .... noContext"+"\n";
  }

  @RequestMapping("/withName")
  public String noContextWithName(@RequestParam(value = "name") String name) {
    return "Hello .... noContextWithName" + name + "\n";
  }
}
