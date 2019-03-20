package com.riane.apilimitrate.controller;

import com.riane.apilimitrate.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConverterController {


    @GetMapping("/test-converter")
    public String test(@RequestParam User user) {
        System.out.println(user.toString());
        return user.toString();
    }

}
