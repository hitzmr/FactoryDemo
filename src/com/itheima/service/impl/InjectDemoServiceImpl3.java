package com.itheima.service.impl;

import com.itheima.service.InjectDemoService;

import java.lang.reflect.Array;
import java.util.*;

public class InjectDemoServiceImpl3 implements InjectDemoService {

    private String[] myString;
    private List myList;
    private Set mySet;
    private Map myMap;
    private Properties myProperties;

    public void setMyString(String[] myString) {
        this.myString = myString;
    }

    public void setMyList(List myList) {
        this.myList = myList;
    }

    public void setMySet(Set mySet) {
        this.mySet = mySet;
    }

    public void setMyMap(Map myMap) {
        this.myMap = myMap;
    }

    public void setMyProperties(Properties myProperties) {
        this.myProperties = myProperties;
    }

    @Override
    public void save() {
        System.out.println("注入方式三：复杂结构注入");
        System.out.println(Arrays.toString(myString));
        System.out.println(myList);
        System.out.println(mySet);
        System.out.println(myMap);
        System.out.println(myProperties);
    }

}
