package com.itheima.ui;

import com.itheima.service.InjectDemoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client3 {
    public static void main(String[] args){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("injectBean.xml");
        /*
         * 方式一， 使用构造方法注入
         */
        InjectDemoService injectDemoService1 = (InjectDemoService) applicationContext.getBean("injectServiceConstructor");
        injectDemoService1.save();

        /*
         * 方式二， 使用Set方法注入
         */
        InjectDemoService injectDemoService2 = (InjectDemoService) applicationContext.getBean("injectServiceSetter");
        injectDemoService1.save();

        /*
         * 方式三， 复杂结构与注入
         */
        InjectDemoService injectDemoService3 = (InjectDemoService) applicationContext.getBean("injectServiceComplex");
        injectDemoService3.save();
    }
}
