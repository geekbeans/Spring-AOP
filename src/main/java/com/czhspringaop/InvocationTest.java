package com.czhspringaop;

import com.czhspringaop.handler.MyInvocationHandler;
import com.czhspringaop.service.CalCu;
import com.czhspringaop.service.impl.CalCuImpl;

public class InvocationTest {
    public static void main(String[] args) {
        //实例化委托对象
        // 相当于你想买房，你人必须到现场
        CalCu calCu = new CalCuImpl();
        //实例化MyInvocationHandler
        // 相当于现在你找一个中介公司，叫中介公司帮你找人看房
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
        //通过myInvocationHandler.bind()方法，将委托对象传入方法中，然后返回一个代理对象
        //代理对象是一个接口类型
        //相当于你人跟着进了公司，去找中介人，在公司中谈好你所有的条件后，中介人就出公司门（返回的是代理对象）
        //帮你去找合适的房源
        CalCu cu =(CalCu) myInvocationHandler.bind(calCu);
        //然后直接调用代理对象的方法
        //根据你所提的要求，（调用你的方法），然后去验房
        cu.sum(1,1);
        cu.minus(2,1);
        cu.plus(3,1);
        cu.div(6,2);
    }
}
