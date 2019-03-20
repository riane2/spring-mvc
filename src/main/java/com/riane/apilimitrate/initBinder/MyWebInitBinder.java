package com.riane.apilimitrate.initBinder;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;

@Component
public class MyWebInitBinder implements WebBindingInitializer {
    @Override
    public void initBinder(WebDataBinder webDataBinder) {
        //webDataBinder.registerCustomEditor(User.class, "XXX");
        webDataBinder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }
}
