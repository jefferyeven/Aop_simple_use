package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Proxy;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    HelloWorld helloWorldImpl1;
    @Autowired
    DetailTest detailTest;
    @Test
    void contextLoads() {
        helloWorldImpl1.doPrint();
    }

    @Test
    void setDetailTest(){
        detailTest.print("jeffery");
    }
    @Test
    void setDetailTest02(){
        System.out.println("返回值: "+detailTest.doPrint("jeffery"));
    }
    @Test
    void testSimple(){
        SimpleExample simpleExample = new SimpleExample();
        simpleExample.orinFun("test");
    }
    @Test
    void tesProxy(){
        SimpleExample simpleExample = new SimpleExample();
        SExample simpleExampleProxy = (SExample) Proxy.newProxyInstance(SimpleProxy.class.getClassLoader(),
                new Class<?>[]{SExample.class},new SimpleProxy(simpleExample));
        simpleExampleProxy.orinFun("test");
    }

}
