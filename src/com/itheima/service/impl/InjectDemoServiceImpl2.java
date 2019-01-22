package com.itheima.service.impl;

import com.itheima.service.InjectDemoService;

import java.util.Date;

public class InjectDemoServiceImpl2 implements InjectDemoService {
    private String name;
    private Date today;

    public void setName(String name) {
        this.name = name;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    @Override
    public void save() {
        System.out.println("注入方式二：Setter方法注入, " + this.name + " " + this.today);
    }

}
