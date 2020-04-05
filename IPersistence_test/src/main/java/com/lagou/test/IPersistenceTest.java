package com.lagou.test;

import com.lagou.dao.IUserDao;
import com.lagou.io.Resources;
import com.lagou.pojo.User;
import com.lagou.sqlSession.SqlSession;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 查询列表
        List<User> all = userDao.findAll();
        for (User user : all) {
            System.out.println(user);
        }

        // 查询对象
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("zhangsan");
        User user2 = userDao.findByCondition(user1);
        System.out.println(user2);

        // 新增
        User user3 = new User();
        user3.setId(3);
        user3.setUsername("zhuhov");
        userDao.add(user3);

        // 修改
        User user4 = new User();
        user4.setId(2);
        user4.setUsername("newhand");
        userDao.modify(user4);

        // 删除
        User user5 = new User();
        user5.setId(1);
        userDao.remove(user5);
    }
}
