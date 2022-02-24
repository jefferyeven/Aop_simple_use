package com.example.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.Arrays;

@Aspect
@Component
public class AopDetailHandler {
    @Pointcut("execution(* com.example.demo.DetailTest.print(..))")
    public void pointCut(){
    }

    @Pointcut("execution(* com.example.demo.DetailTest.doPrint(String))")
    public void pointCut2(){
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) throws Throwable {
        System.out.println("得到切片的参数"+Arrays.toString(joinPoint.getArgs()));
        System.out.println("得到运行的类"+joinPoint.getSignature());
        System.out.println("代理----前----CurrentTime = " + System.currentTimeMillis());
    }

    @Around("pointCut2()")
    public Object run1(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // before
        // 获取方法名
        String methodName = proceedingJoinPoint.getSignature().toString();
        System.out.println("方法名"+methodName);
        Object[] args = proceedingJoinPoint.getArgs();
        System.out.println("参数"+ Arrays.toString(args));
        // 获取掉切片目标类
        String entity = proceedingJoinPoint.getTarget().getClass().getName();
        System.out.println("获取调切片"+entity);
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();//test方法的返回值
            // after
            System.out.println(result.toString());
        } catch (Exception ex) {
            // after throwing
            System.out.println(ex.toString());
        }
        return result;
    }
}
