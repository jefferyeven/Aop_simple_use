package com.example.demo;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopHandler {

    @Pointcut("execution(* com.example.demo.HelloWorld.*(..))")
    public void test(){

    }
    @Before("test()")
    public void before() throws Throwable {
        System.out.println("代理----前----CurrentTime = " + System.currentTimeMillis());
    }

    @After("test()")
    public void afterReturning() throws Throwable {
        System.out.println("代理----后----CurrentTime = " + System.currentTimeMillis());
    }
}
