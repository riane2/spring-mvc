package com.riane.apilimitrate.convertconfig;

import com.riane.apilimitrate.controller.InitBinderController;
import com.riane.apilimitrate.convert.MyStringToUserConvert;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.web.bind.annotation.InitBinder;

import com.riane.apilimitrate.initBinder.MyWebInitBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.HashSet;
import java.util.Set;

/**
 * 装配自定义类型转换器的几种方法：
 * 1、通过{@linkplain ConversionServiceFactoryBean}的setConverters方法，装配自定义的Converter，
 * 自定义的convert可以通过以下三种方式：
 * 1.1：实现{@linkplain Converter}借口
 * 1.2：实现{@linkplain ConverterFactory}接口
 * 1.3：实现{@linkplain GenericConverter}接口
 * 2.controller类中通过{@linkplain InitBinder}注解实现绑定，参考类{@linkplain InitBinderController}
 * 3.全局配置参考{@linkplain MyWebInitBinder},主要是实现{@linkplain WebBindingInitializer} 接口,
 * 并通过RequestMappingHandlerAdapter#setWebBindingInitializer()方法设置
 */


@Configuration
public class MyConverterConfig {


    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter(MyWebInitBinder initBinder) {
        RequestMappingHandlerAdapter handlerAdapter = new RequestMappingHandlerAdapter();
        handlerAdapter.setWebBindingInitializer(initBinder);
        return handlerAdapter;
    }


    @Bean
    public ConversionServiceFactoryBean conversionServiceFactoryBean(MyStringToUserConvert convert) {
        ConversionServiceFactoryBean serviceFactoryBean = new ConversionServiceFactoryBean();
        Set<Converter> set = new HashSet<>();
        set.add(convert);
        serviceFactoryBean.setConverters(set);
        return serviceFactoryBean;
    }

}
