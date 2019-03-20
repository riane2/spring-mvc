package com.riane.apilimitrate.controller;

import com.riane.apilimitrate.annotation.LimitRate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterfaceLimitController {


    @GetMapping("/test1")
    @LimitRate(5)
    public String test1() {
        return "this is test one";
    }

    @LimitRate(7)
    @GetMapping("/test2")
    public String test2() {
        return "this is test two";
    }
}
