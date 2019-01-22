package com.itheima.service.impl;

import com.itheima.service.InjectDemoService;

import java.util.Date;

public class InjectDemoServiceImpl1 implements InjectDemoService {
    private String name;
    private Date today;

    public InjectDemoServiceImpl1(String name, Date today){
        this.name = name;
        this.today = today;
    }

    @Override
    public void save() {
        System.out.println("注入方式一：构造方法注入, " + this.name + " " + this.today);
    }

}
