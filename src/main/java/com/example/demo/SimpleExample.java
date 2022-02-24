package com.example.demo;

import java.lang.reflect.InvocationHandler;
import java.util.Date;

public class SimpleExample implements SExample {
    @Override
    public String orinFun(String arg){
        return  arg;
    }
}

