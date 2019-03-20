package com.riane.apilimitrate.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.HashMap;
import java.util.Map;

@RestController
@SessionAttributes("user")
public class MySessionController {


    @ModelAttribute("user")
    public Map<String, String> name() {
        Map<String, String> hashMap = new HashMap<>(2);
        hashMap.put("name", "riane");
        hashMap.put("age", "20");
        return hashMap;
    }

    @GetMapping("test1-session")
    public String test1(@ModelAttribute("user") Map<String, String> user) {
        if (user != null) {
            user.put("name", "riane2");
        }
        return user.toString();
    }

    @GetMapping("test2-session")
    public String test2(ModelMap modelMap, SessionStatus sessionStatus) {
        Map<String, String> user = (Map<String, String>) modelMap.get("user");
        sessionStatus.setComplete(); //让spring mvc清除本处理器对应的会话属性
        return user.get("name") + "," + user.get("age");
    }

}
