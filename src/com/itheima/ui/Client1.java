package com.itheima.ui;

import com.itheima.factory.BeanFactory;
import com.itheima.service.ICustomerService;
import com.itheima.service.impl.ICustomerServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * 我们在开发的时候需要遵循的规范：
 *      编译时不依赖，运行时才依赖
 *  解决依赖关系的办法：
 *      使用反射创建对象
 *  使用反射创建对象引发的新问题：
 *      hardcode，如需改动，还是要修改代码
 *  解决反射创建对象问题的办法：
 *      使用配置文件，通过读取配置文件来反射创建类对象
 *
 */
public class Client1 {
    public static void main(String[] args){
        /*
         * 方式一：
         *      需要初始化实现层的具体对象
         *      弊端：
         *          当ICustomerServiceImpl还没有被编写出来的时候，编译就无法通过，无法测试
         *
         */
        System.out.println("方式一：");
        ICustomerService iCustomerService1 = new ICustomerServiceImpl();
        iCustomerService1.saveCustomer();
        System.out.println();

        /*
         * 方式二：
         *      通过工厂类，使用反射得到一个对象
         *      优势:
         *           在于当ICustomerServiceImpl没有被编辑的时候编译仍然能够通过
         *           只是会在运行时会抛出无法找到类的异常
         *      缺点：
         *              仍然是通过hardcode的方式找到具体的类，当需要修改的时候只能修改源码
         *
         */
        System.out.println("方式二：");
        ICustomerService iCustomerService2 = BeanFactory.getICustomerService();
        iCustomerService2.saveCustomer();
        System.out.println();

        /*
         * 方式三：
         *      通过工厂类，使用反射得到一个对象
         *      优势:
         *           在于当ICustomerServiceImpl没有被编辑的时候编译仍然能够通过
         *           只是会在运行时会抛出无法找到类的异常
         *      缺点：
         *              仍然是通过hardcode的方式找到具体的类，当需要修改的时候只能修改源码
         *
         */
        System.out.println("方式三：");
        ICustomerService iCustomerService3 = (ICustomerService) BeanFactory.getBeanByClassLoader("CUSTOMERSERVICE");

        ICustomerService iCustomerService4 = (ICustomerService) BeanFactory.getBeanByResBundle("CUSTOMERSERVICE");
        iCustomerService4.saveCustomer();
        System.out.println();

        /*
         * 使用静态方法，在工厂类初始化的时候将配置文件中的类一次创建对象，并保存在工厂类的成员变量中。
         * 如此便保证了我在每次使用的时候使用的都是同一个对象，不会多次重复创建
         */
        System.out.println("方式四");
        ICustomerService iCustomerService5 = (ICustomerService) BeanFactory.getBeanFromMap("CUSTOMERSERVICE");
        iCustomerService5.saveCustomer();
        System.out.println();

        /*
         * 通过Spring的ApplicationContext，将对象路径保存在xml文件中
         */
        System.out.println("方式五");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        ICustomerService iCustomerService6 = (ICustomerService) applicationContext.getBean("CUSTOMERSERVICE");
        iCustomerService6.saveCustomer();
        System.out.println();

    }
}
