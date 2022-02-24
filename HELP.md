# Aop 的作用
Aop: 面向切面编程
aop 的主要作用是运行一个函数的前后，运行另外的函数。

**例子： 我们以一个日志为例，我们运行一个函数，需要记录这个函数的运行时间 和他的运行结果**
**最简单的函数**
```
public class SimpleExample implements SExample {
    @Override
    public String orinFun(String arg){
        return  arg;
    }
}
```

### 最简单的写法
我们直接在该函数记录时间和结果

```
public String orinFun(String arg){
        System.out.println("运行时间"+new Date().toString());
        System.out.println("结果"+arg);
        return  arg;
    }
```
当我们需要打印多个函数的运行时间这样的写法就非常冗余，且不易扩展，我们可以通过代理来解决。

### 代理
接口
```
public interface SExample {
    String orinFun(String arg);
}
```
代理

```
public class SimpleProxy implements InvocationHandler {

    private Object obj;

    public SimpleProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();//你也可以根据代理名字的不同，做不同的代理方式 if(methodName.equals("*"))
        System.out.println("运行时间"+new Date().toString());
        Object res = method.invoke(obj, args);
        System.out.println("结果"+res);
        return res;
    }
}
```
测试
```
 @Test
    void tesProxy(){
        SimpleExample simpleExample = new SimpleExample();
        SExample simpleExampleProxy = (SExample) Proxy.newProxyInstance(SimpleProxy.class.getClassLoader(),
                new Class<?>[]{SExample.class},new SimpleProxy(simpleExample));
        simpleExampleProxy.orinFun("test");
    }
```
这样写就很方便了，一个类只需要写一个代理就行
spring boot 提供更加方便的写法就是aop

# AOP的使用

## 1.引入包
```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
```
## 2.定义切片类
A.在类前面加上 @Aspect @Component注解
B. 定义切面 @Pointcut("execution(* com.example.demo.HelloWorld.*(..))")，既在那些函数外增添操作
C.定义具体的执行操作
D.什么时候执行操作  @Before  @after AfterThrowing...
```
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
```

# Aop Around

我们可以将 before 和 after 和 afterthrowing 都放在Around 来做,更加的方便
```
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

```

#gitee
https://gitee.com/jefferyeven/Aop_simple_use
