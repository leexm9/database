package com.leexm.demo.database.mybatis;

import com.leexm.demo.database.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

/**
 * @author leexm
 * @date 2020/7/8 18:49
 */
public class MybatisTest {

    @Test
    public void testGetById() {
        User user = null;
        try (SqlSession sqlSession = MybatisUtil.getSqlSessionFactory().openSession()) {
            UserDao userMapper = sqlSession.getMapper(UserDao.class);
            user = userMapper.getById(1);
            sqlSession.commit();
        }
        System.out.println(user);
        Assert.assertNotNull(user);
    }

    @Test
    public void test() throws IOException {
        String resource = "mybatis/mybatis-self-config.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionManager sessionManager = SqlSessionManager.newInstance(reader);
        sessionManager.startManagedSession();
        UserDao userDao = sessionManager.getMapper(UserDao.class);
        User user = userDao.getById(2);
        System.out.println(user);

        UserDao userDao1 = sessionManager.getMapper(UserDao.class);
        User user1 = userDao1.getById(1);
        System.out.println(user1);
    }

}
