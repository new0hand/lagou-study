package com.lagou.edu.factory;

import com.lagou.edu.annotation.Autowired;
import com.lagou.edu.annotation.Service;
import com.lagou.edu.annotation.Transactional;
import com.lagou.edu.pojo.Account;
import com.lagou.edu.utils.TransactionManager;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 应癫
 *
 *
 * 代理对象工厂：生成代理对象的
 */
@Service
public class ProxyFactory {
    @Autowired
    private TransactionManager transactionManager;

    private Map<Class, List<String>> enhanceMap = new HashMap<>();


    public void addToEnhanceMap(Class aClass, List<String> methodNameList) {
        this.enhanceMap.put(aClass, methodNameList);
    }
    /**
     * Jdk动态代理
     * @param obj  委托对象
     * @return   代理对象
     */
    public Object getJdkProxy(Object obj) {
        final Class<?> aClass = obj.getClass();
        // 查找改类被增强的方法
        List<String> enhanceMethodList = enhanceMap.get(aClass);

        // 获取代理对象
        return  Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object result = null;

                        // 如果方法被增强，执行增强逻辑，否则只执行原方法
                        if (enhanceMethodList.contains(method.getName())) {
                            try{
                                // 开启事务(关闭事务的自动提交)
                                transactionManager.beginTransaction();

                                result = method.invoke(obj,args);

                                // 提交事务
                                transactionManager.commit();
                            }catch (Exception e) {
                                e.printStackTrace();
                                // 回滚事务
                                transactionManager.rollback();

                                // 抛出异常便于上层servlet捕获
                                throw e;

                            }
                        } else {
                            result = method.invoke(obj,args);
                        }

                        return result;
                    }
                });

    }


    /**
     * 使用cglib动态代理生成代理对象
     * @param obj 委托对象
     * @return
     */
    public Object getCglibProxy(Object obj) {
        final Class<?> aClass = obj.getClass();
        // 查找改类被增强的方法
        List<String> enhanceMethodList = enhanceMap.get(aClass);

        return  Enhancer.create(aClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object result = null;
                // 如果方法被增强，执行增强逻辑，否则只执行原方法
                if (enhanceMethodList.contains(method.getName())) {
                    try{
                        // 开启事务(关闭事务的自动提交)
                        transactionManager.beginTransaction();

                        result = method.invoke(obj,objects);

                        // 提交事务
                        transactionManager.commit();
                    }catch (Exception e) {
                        e.printStackTrace();
                        // 回滚事务
                        transactionManager.rollback();

                        // 抛出异常便于上层servlet捕获
                        throw e;

                    }
                } else {
                    result = method.invoke(obj,objects);
                }

                return result;
            }
        });
    }
}
