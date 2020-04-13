package com.lagou.edu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.lagou.edu.annotation.Service;

/**
 * @author 应癫
 */
@Service
public class DruidUtils {

//    private DruidUtils(){
//    }

    private static DruidDataSource druidDataSource = new DruidDataSource();


    static {
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/study");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("");

    }

    public static DruidDataSource getInstance() {
        return druidDataSource;
    }

}
