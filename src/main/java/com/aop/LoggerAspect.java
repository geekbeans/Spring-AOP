package com.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
//@Aspect注解目的是让其成为一个切面对象
@Aspect
//@Component注解目的是让IOC容器管理此切面对象
@Component
//创建切面对象，并定义切面方法，根据注解放入合适的切面位置
public class LoggerAspect {
    //注解@Before表明此切面方法位置位于业务代码之前处理
    //execution()表示处理的方法位置
    //public int com.czhspringaop.service.impl.CalCuImpl.*(..)表示的是：
    //在com.czhspringaop.service.impl包下的CalCuImpl类中的所有方法
    //其中*通配符所有方法，（..）通配所有方法参数
    @Before(value = "execution(public int com.czhspringaop.service.impl.CalCuImpl.*(..))")
    public void before(JoinPoint joinPoint){//然后传入一个参数joinPoint
        //joinPoint的意思是连接点，连接切面的点，指的是业务中的各自方法信息
        //所以有了joinPoint可以获取原来业务方法信息中的方法名和参数
        //获取方法名
        String name = joinPoint.getSignature().getName();
        //获取参数
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println(name+"的参数是"+args);
    }

    //表示在业务方法之后执行的切面方法
    @After(value = "execution(public int com.czhspringaop.service.impl.CalCuImpl.*(..))")
    public void after(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println(name+"方法执行完毕");
    }

    //业务方法执行后返回结果的切面方法
    @AfterReturning(value = "execution(public int com.czhspringaop.service.impl.CalCuImpl.*(..))",returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result){
        String name = joinPoint.getSignature().getName();
        System.out.println(name+"方法的结果是"+result);
    }

    //业务方法执行后抛出异常的切面方法
    @AfterThrowing(value = "execution(public int com.czhspringaop.service.impl.CalCuImpl.*(..))",throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint,Exception exception){
        String name = joinPoint.getSignature().getName();
        System.out.println(name+"方法的异常为"+exception);
    }
}
