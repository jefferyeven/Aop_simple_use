package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class HelloWorld {

    public void printHelloWorld() {
        System.out.println("------11111------按下HelloWorld1.printHelloWorld()-----11111111-------");
    }

    public void doPrint() {
        System.out.println("------1111111------打印HelloWorldImpl1-----1111111------");
    }
}
