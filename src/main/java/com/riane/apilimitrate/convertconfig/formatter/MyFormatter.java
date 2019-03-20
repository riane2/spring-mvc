package com.riane.apilimitrate.convertconfig.formatter;

import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

/**
 * 格式化：请求和服务器可能在不同的操作系统上，参数的格式可能不一致
 */
public class MyFormatter {


    /**
     * 默认注册了：时间和数字格式化，
     * 参考代码{@linkplain DefaultFormattingConversionService#addDefaultFormatters(org.springframework.format.FormatterRegistry)}
     * <p>
     * 因此可以使用{@linkplain NumberFormat}注解和{@linkplain DateTimeFormat}注解
     *
     * @return
     */
    @Bean
    public FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean() {
        FormattingConversionServiceFactoryBean serviceFactoryBean = new FormattingConversionServiceFactoryBean();
        return serviceFactoryBean;
    }

}
