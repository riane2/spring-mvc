package com.riane.apilimitrate.convert;

import com.riane.apilimitrate.bean.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 将形如： “iane:20:riane5” 的字符串解析成user类
 */
@Component
public class MyStringToUserConvert implements Converter<String, User> {

    @Override
    public User convert(String s) {
        User user = new User();
        if (!s.isEmpty()) {
            String[] split = s.split(":");
            user.setName(split[0]);
            user.setAge(Integer.parseInt(split[1]));
            user.setRealName(split[2]);
        }
        return user;
    }
}
