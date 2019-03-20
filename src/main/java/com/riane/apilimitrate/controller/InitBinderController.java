package com.riane.apilimitrate.controller;

import com.riane.apilimitrate.bean.User;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitBinderController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        /**
         * 第一个自定义属性编辑器
         * 第二个自定义格式化
         */
        //dataBinder.registerCustomEditor(User.class, "XXX");
        dataBinder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }
}
