package com.riane.apilimitrate.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.security.Principal;
import java.util.List;

@RestController
public class MyMvcController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @GetMapping("/principal")
    public String test(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/http-entity")
    public String test1(HttpEntity<String> entity) {
        System.out.println(entity.getHeaders().getContentLength());
        return entity.getBody();
    }

    @GetMapping("/response-entity")
    public ResponseEntity<String> test2(@RequestBody String name) {
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(name, HttpStatus.OK);
        RequestMappingHandlerAdapter handlerAdapter = applicationContext.getBean(RequestMappingHandlerAdapter.class);
        if (handlerAdapter != null) {
            List<HttpMessageConverter<?>> converters = handlerAdapter.getMessageConverters();
            converters.forEach(System.out::println);
        }
        return responseEntity;
    }


    @ModelAttribute("before")
    public String beforeControllerMeth() {
        return "this is before";
    }

    @GetMapping("test-model-attr")
    public String testBeforeControllerMeth(Model model) {
        if (model != null) {
            Object before = model.asMap().get("before");
            System.out.println(before);
            return String.valueOf(before);
        }
        return "null";
    }

    @GetMapping("test-model-map")
    public String testModelMap(ModelMap model) {
        if (model != null) {
            Object before = model.getOrDefault("before","default");
            System.out.println(before);
            return String.valueOf(before);
        }
        return "null";
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
