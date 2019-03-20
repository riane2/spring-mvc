package com.riane.apilimitrate.aspect;

import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.RateLimiter;
import com.riane.apilimitrate.annotation.LimitRate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Aspect
@Scope
public class RateLimitAspect {


    private static ConcurrentHashMap<String, RateLimiter> map = new ConcurrentHashMap<>();

    private static ObjectMapper objectMapper = new ObjectMapper();

    private RateLimiter rateLimiter;

    @Autowired
    private HttpServletResponse response;

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Pointcut("@annotation(com.riane.apilimitrate.annotation.LimitRate)")
    public void serviceLimit() {

    }

    @Around("serviceLimit()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = null;
        //获取拦截的方法名
        Signature signature =
                joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //返回被织入增强处理的目标对象
        Object target = joinPoint.getTarget();
        //获取注解信息
        String functionName = methodSignature.getName();//注解所在方法名区分不同的限流策略
        Method method = target.getClass().getMethod(functionName, methodSignature.getParameterTypes());
        LimitRate annotation = method.getAnnotation(LimitRate.class);
        if (annotation != null) {
            double limitNum = annotation.value(); //获取注解每秒加桶的token

            if (map.containsKey(functionName)) {
                rateLimiter = map.get(functionName);
            } else {
                rateLimiter = RateLimiter.create(limitNum);
                map.put(functionName, rateLimiter);
            }

            if (rateLimiter.tryAcquire()) {
                obj = joinPoint.proceed();
            } else {
                //拒绝请求，服务降级
                String result = objectMapper.writeValueAsString("拒绝执行方法");
                System.out.println("拒绝执行请求：" + result);
                outErrorResult(result);
            }

        }
        return obj;
    }

    public void outErrorResult(String result) {
        //response.setContentType("application");
        try (OutputStream outputStream = response.getOutputStream()) {
            outputStream.write(result.getBytes("utf-8"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
