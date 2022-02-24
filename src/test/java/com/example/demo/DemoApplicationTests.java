package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    HelloWorldImpl1 helloWorldImpl1;
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

}
