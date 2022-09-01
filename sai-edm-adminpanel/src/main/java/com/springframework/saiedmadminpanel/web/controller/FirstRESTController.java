package com.springframework.saiedmadminpanel.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class FirstRESTController {

    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
    }
}
