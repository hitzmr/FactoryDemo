package com.itheima.ui;

import com.itheima.service.ICustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Bean创建的两种规则
 *      1、BeanFactory
 *          延迟加载，Bean对象什么时候用什么时候创建
 *      2、ApplicationContext
 *          立即加载，只要配置文件读取完毕就立马创建对象
 *
 *  Bean的三种创建方式
 *      1、调用默认的无参构造函数创建。 如果类中没有默认的无参构造函数，那么此时会抛异常
 *      2、使用静态工厂中的方法创建对象
 *      3、使用实例工厂中的方法
 *
 *  Bean的作用范围
 *      可以通过配置的方式来调整作用范围
 *      配置属性：bean标签的scope属性
 *      属性的取值：
 *          singleton ： 单例（默认值）
 *          prototype ： 多例
 *          request ： 作用范围时一次请求和当前请求的转发
 *          session ： 作用范围时一次会话
 *          gloablesession
 *
 *  Bean的生命周期
 *      涉及bean标签的两个属性
 *           init-method
 *           destroy-method
 *      单例：
 *          容器一旦创建，对象就被创建了，容器被销毁，则对象被销毁
 *
 *      多例
 *
 */
public class Client2 {
    public static void main(String[] args) {


        /*
         * 使用静态工厂中的方法创建对象， 需要使用bean标签中的factory-method属性，指定创建对象的方法
         */
        System.out.println("使用静态工厂中的方法创建对象");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        ICustomerService iCustomerService1 = (ICustomerService) applicationContext.getBean("staticCustomerService");
        iCustomerService1.saveCustomer();
        System.out.println();

        /*
         * 使用实例工厂中的方法创建对象， 需要使用bean标签中的factory-method属性，指定创建对象的方法
         */
        System.out.println("使用实例工厂中的方法创建对象");
        ICustomerService iCustomerService2 = (ICustomerService) applicationContext.getBean("instanceCustomerService");
        iCustomerService2.saveCustomer();
        System.out.println();

        ICustomerService iCustomerService3 = (ICustomerService) applicationContext.getBean("instanceCustomerService");
        iCustomerService3.saveCustomer();

        // true: 两次拿到的对象一样，单例； false ：两次拿到的对象不一样，多例
        // 通过配置bean标签的scope属性， 可以实现单例和多例的转换
        System.out.println("使用scope属性修改单例多例演示， 是否时单例 ：" + (iCustomerService3 == iCustomerService2));
        System.out.println();

        // 演示init-method 和 destroy-method
        ICustomerService iCustomerService4 = (ICustomerService) applicationContext.getBean("CUSTOMERSERVICE");
        iCustomerService4.saveCustomer();
    }
}
