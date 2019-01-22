package com.itheima.dao.impl;

import com.itheima.dao.ICustomerDao;

/*
 * 模拟持久层
 */
public class ICustomerDaoImpl implements ICustomerDao {
    @Override
    public void saveCustomer() {
        System.out.println("持久层保存了客户");
    }
}
