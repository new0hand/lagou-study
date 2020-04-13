package com.lagou.edu.factory;

import com.lagou.edu.annotation.Autowired;
import com.lagou.edu.annotation.Service;
import com.lagou.edu.annotation.Transactional;
import com.lagou.edu.utils.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author 应癫
 *
 * 工厂类，生产对象（使用反射技术）
 */
public class BeanFactory {
    private static Map<String,Object> map = new HashMap<>();  // 存储对象

    private static Map<Class, Class> interfaceClassMap = new HashMap<>(); // 接口-》类 对应map


    static {
        // 扫描所有类
        Set<Class<?>> classSet = ClassUtils.getClassSet("com.lagou.edu");

        // 设置接口、实现类映射
        setInterfaceClassMap(classSet);

        // ioc功能
        iocFunc(classSet);

        // di功能
        diFunc(classSet);

        // aop功能
        aopFunc(classSet);

        System.out.println(map);
    }

    private static void aopFunc(Set<Class<?>> classSet) {
        classSet.forEach(aClass -> {
            if (aClass.getModifiers() != 1) {
                return;
            }

            String name = aClass.getName();
            Object o = map.get(name);

            // 类的所有方法
            Method[] declaredMethods = aClass.getDeclaredMethods();
            // 被增强的方法
            List<String> enhanceMethodNameList = new ArrayList<>();

            // 如果类上有注解，类的全部方法增强
            // 否则遍历方法，只增强有注解的方法
            if (aClass.isAnnotationPresent(Transactional.class)) {
                for (Method declaredMethod : declaredMethods) {
                    enhanceMethodNameList.add(declaredMethod.getName());
                }
            } else {
                for (Method declaredMethod : declaredMethods) {
                    if (declaredMethod.isAnnotationPresent(Transactional.class)) {
                        String declaredMethodName = declaredMethod.getName();
                        enhanceMethodNameList.add(declaredMethodName);
                    }
                }
            }

            // 有增强方法，返回代理对象
            if (enhanceMethodNameList.size() > 0) {
                ProxyFactory proxyFactory = (ProxyFactory) map.get(ProxyFactory.class.getName());

                // 给代理类enhanceMap添加元素
                proxyFactory.addToEnhanceMap(aClass, enhanceMethodNameList);

                // 判断是否实现了接口
                Boolean hasInterface = hasInterface(aClass);
                Object proxy = null;
                if (hasInterface) {
                    proxy = proxyFactory.getJdkProxy(o);
                } else {
                    proxy = proxyFactory.getCglibProxy(o);
                }

                // 代理类替换委托类
                map.put(name, proxy);
            }
        });
    }

    private static Boolean hasInterface(Class<?> aClass) {
        for (Map.Entry<Class, Class> classClassEntry : interfaceClassMap.entrySet()) {
            if (classClassEntry.getValue().equals(aClass)) {
                return true;
            }
        }
        return false;
    }

    private static void diFunc(Set<Class<?>> classSet) {
        classSet.forEach(aClass -> {
            if (aClass.getModifiers() != 1) {
                return;
            }

            String parent = aClass.getName();
            Object parentObject = map.get(parent);

            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    try {
                        declaredField.setAccessible(true);
                        // 获取class
                        Class<?> childClass = declaredField.getType();

                        // 如果是接口，获取接口的实现类名称
                        if (interfaceClassMap.containsKey(childClass)) {
                            childClass = interfaceClassMap.get(childClass);
                        }
                        String child = childClass.getName();

                        // 从"缓存"中取对象
                        Object childObject = map.get(child);

                        // 给service对象赋值
                        declaredField.set(parentObject, childObject);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private static void iocFunc(Set<Class<?>> classSet) {
        classSet.forEach(aClass -> {
            if (aClass.getModifiers() != 1) {
                return;
            }

            // 获取对象
            Object o = null;
            try {
                o = aClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            String name = aClass.getName();
            if (aClass.isAnnotationPresent(Service.class)) {
                // 获取value属性值
                Service annotation = aClass.getAnnotation(Service.class);
                String value = annotation.value();
                if (StringUtils.isNotEmpty(value)) {
                    name = value;
                }

                map.put(name, o);
            }
        });
    }

    private static void setInterfaceClassMap(Set<Class<?>> classSet) {
        classSet.forEach(aClass -> {
            if (aClass.getModifiers() != 1) {
                return;
            }

            Class<?>[] interfaces = aClass.getInterfaces();
            for (Class<?> anInterface : interfaces) {
                interfaceClassMap.put(anInterface, aClass);
            }
        });
    }


    // 任务二：对外提供获取实例对象的接口（根据id获取）
    public static  Object getBean(String id) {
        Object o = map.get(id);
        return o;
    }

}
