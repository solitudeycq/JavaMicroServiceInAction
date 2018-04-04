package com.microservice.firstboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/firstboot")
public class FirstBootController {
    @RequestMapping(value="/sayHello",method = RequestMethod.GET)
    public String sayHello(){
        return "Hello,this is my first boot program!!!";
    }
}
