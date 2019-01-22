package com.itheima.service.impl;

import com.itheima.dao.ICustomerDao;
import com.itheima.factory.BeanFactory;
import com.itheima.service.ICustomerService;

/*
 * 业务层实现类
 */
public class ICustomerServiceImpl implements ICustomerService {
//    private ICustomerDao iCustomerDao = new ICustomerDaoImpl();
    private ICustomerDao iCustomerDao = (ICustomerDao) BeanFactory.getBeanByResBundle("ICUSTOMERDAO");
//    private ICustomerDao iCustomerDao = (ICustomerDao) BeanFactory.getBeanFromMap("ICUSTOMERDAO");
    @Override
    public void saveCustomer() {
        System.out.println("业务层调用持久层");
        iCustomerDao.saveCustomer();
    }

    //Bean的三种创建方式之一，调用默认的无参构造函数创建。 如果类中没有默认的无参构造函数，那么此时会抛异常
//    public ICustomerServiceImpl(String name){
//        System.out.println("实例化对象");
//    };

    public ICustomerServiceImpl(){
        System.out.println("实例化对象");
    };

    public void init(){
        System.out.println("对象init");
    }

    public  void destroy(){
        System.out.println("对象destroy");
    }

}
