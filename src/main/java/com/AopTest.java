package com;

import com.czhspringaop.service.CalCu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {
    public static void main(String[] args) {
        //获取配置方法
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aop.xml");
        //获取代理对象
        //此处getBean()方法中的参数默认就是impl包下的方法首字母小写calCuImpl
        //如果需要改动参数，只需要在CalCuImpl方法中的@Component("other")改成other参数即可
        //此处getBean()中的参数传入other
        CalCu proxy = (CalCu) applicationContext.getBean("calCuImpl");
        //调用代理方法
        proxy.sum(1,2);
        proxy.div(3,0);
    }
}
