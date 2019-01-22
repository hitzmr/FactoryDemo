package com.itheima.factory;

import com.itheima.service.ICustomerService;
import com.itheima.service.impl.ICustomerServiceImpl;

/*
 * 实例工厂的例子
 */
public class InstanceFactory {
    public ICustomerService getICustomerService(){
        return new ICustomerServiceImpl();
    }

}
