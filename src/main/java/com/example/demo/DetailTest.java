package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class DetailTest {

    public void print(String name) {
        System.out.println("-------print-----"+name+"------22222-----");
    }

    public String doPrint(String name) {
        System.out.println("-------doPrint-----"+name+"------22222-----");
        return name;
    }
}
