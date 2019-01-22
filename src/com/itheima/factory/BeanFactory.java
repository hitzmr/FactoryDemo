package com.itheima.factory;

import com.itheima.dao.ICustomerDao;
import com.itheima.service.ICustomerService;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/*
 * 工厂类， 用于返回一个对象
 */
public class BeanFactory {

    /*
     * 方式一
     *      通过反射，hardcode
     */
    public static ICustomerService getICustomerService() {
        try {
            // 此处要注意返回的时实现类里面的对象
            return (ICustomerService) Class.forName("com.itheima.service.impl.ICustomerServiceImpl").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ICustomerDao getICustomerDao() {
        try {
            return (ICustomerDao) Class.forName("com.itheima.dao.impl.ICustomerDaoImpl").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /*
     * 方式二：
     *      通过类加载器读取配置文件，从配置文件中读取类名，然后使用反射得到相应的对象
     */
    private static Properties properties = new Properties();

    /*
     * 静态代码块用来给对象赋值
     */
    static {
        try {
            // 使用类加载器读取文件，文件徐放在相应的应用的classes文件夹下
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");

            /*
             * 不能使用下面这行的形式，因为Web工程发布之后就没有了src这个目录，因此肯定读不到。
             * FileInputStream inputStream = new FileInputStream("src/bean.properties");
             *
             */
            properties.load(in);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("读取配置文件失败" + e);
        }
    }

    public static Object getBeanByClassLoader(String beanName) {
        try {
            return Class.forName(properties.getProperty(beanName)).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /*
     * 方式三：
     *      使用ResourceBundle
     *      它只能用于读取.properties文件，其他类型的文件读不了
     *      它只能用于读取，不能用于写入
     *      它只能读取类路径下的，其它地方的读不了
     *      由于它时通过包名.文件名方式读取文件的，所以不要写后缀名
     */
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("bean");

    public static Object getBeanByResBundle(String beanName) {
        try {
            return Class.forName(resourceBundle.getString(beanName)).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * 如上的几种形式每次使用都会返回一个new instance， 可以优化。
     * 声明一个静态的成员变量，用于保存所有的对象。
     * 使用静态代码块，在类初始化的时候遍历配置文件中的所有key-value，将其存放在静态成员变量中，然后每次使用直接从静态成员变量中读取
     */
    private static Map<String, Object> beans = new HashMap<String, Object>();

    static {
        Enumeration<String> keys = resourceBundle.getKeys();
        String key = null;
        try {
            while (keys.hasMoreElements()) {
                key = keys.nextElement();
                beans.put(key, Class.forName(resourceBundle.getString(key)).getDeclaredConstructor().newInstance());
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化容器出错" + e);
        }
    }

    public static Object getBeanFromMap(String beanName) {
        return beans.get(beanName);
    }

}
