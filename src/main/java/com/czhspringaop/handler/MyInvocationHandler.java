package com.czhspringaop.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

//MyInvocationHandler这个类不是代理类，它是帮助我们创建代理类的类
//InvocationHandler 这个接口就具备创建动态代理类的功能
public class MyInvocationHandler implements InvocationHandler {
    //1.创建一个委托对象，相当于委托人，这个委托人是求别人办事，比如求中介租房
    //代理对象，相当于真正干活跑路的人，比如租房中介
    Object object = null;

    //2.返回代理对象,传入的是委托对象
    public Object bind(Object object){
        this.object = object;
        //返回代理实例，即代理对象
        //返回一个对象，肯定需要一个类来创建，什么类，当然是动态代理类。
        //如何获取，先获取委托对象的运行时类object.getClass
        //再通过运行试类获取到类加载器object.getClass().getClassLoader()
        //有了类加载器就可以生成动态的类
        //如何生成动态的类：代理类全部拥有的委托类的所有功能
        //如何描述第二个参数，利用反射思想，委托对象所对应的类的运行时类object.getClass()
        //然后object.getClass().getInterfaces()表示获取所有接口
        //获取到了接口就可以得到全部方法，然后动态创建就可以
        //this是通过MyInvocationHandler来创建一个代理对象
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),this);
    }

    //要将委托对象传入到代理对象中，相当于现在委托人求代理人办事
    //invoke方法就是来创建切面所需要的功能
    //也就是将主代码中非业务方法：比如日志等剥离出来
    //其中method就是对应的需要剥离的非业务方法
    //args就是非业务方法中的参数
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName()+"方法对应的参数是"+Arrays.toString(args));
        //业务方法写在中间,还是通过反射机制创建，第一个参数：调用的还是委托对象的方法，第二个参数就是非业务方法中的参数
        Object result = method.invoke(this.object, args);
        System.out.println(method.getName()+"结果是"+result);
        return result;
    }
}
