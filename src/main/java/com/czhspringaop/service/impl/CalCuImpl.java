package com.czhspringaop.service.impl;

import com.czhspringaop.service.CalCu;
import org.springframework.stereotype.Component;

@Component
public class CalCuImpl implements CalCu {
    public int sum(int a, int b) {
        return a+b;
    }

    public int minus(int a, int b) {
        return a-b;
    }

    public int plus(int a, int b) {
        return a*b;
    }

    public int div(int a, int b) {
        return a/b;
    }
}
