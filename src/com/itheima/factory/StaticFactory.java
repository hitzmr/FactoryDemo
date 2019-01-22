package com.itheima.factory;

import com.itheima.service.ICustomerService;
import com.itheima.service.impl.ICustomerServiceImpl;
/*
 * 模拟一个静态工厂
 */
public class StaticFactory {
    public static ICustomerService getICustomerService(){
        return new ICustomerServiceImpl();
    }

}
