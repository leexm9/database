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

    private String resource = "mybatis/mybatis-self-config.xml";

    @Test
    public void testGetById() {
        User user = null;
        try (SqlSession sqlSession = MybatisUtil.getSqlSessionFactory().openSession()) {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            user = userDao.getById(1);

            // 命中一级缓存
            UserDao userDao1 = sqlSession.getMapper(UserDao.class);
            System.out.println(userDao1.getById(1));
            // commit 的时候会将一级缓存 缓存到 二级缓存
            sqlSession.commit();
        }
        System.out.println(user);
        // 命中二级缓存
        try (SqlSession sqlSession = MybatisUtil.getSqlSessionFactory().openSession()) {
            user = sqlSession.selectOne("com.leexm.demo.database.mybatis.UserDao.getById", 1);
            System.out.println(user.getName());
        }
        Assert.assertNotNull(user);
    }

    @Test
    public void test2() throws IOException {
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionManager sqlSessionManager = SqlSessionManager.newInstance(reader);
        User user = null;
        try (SqlSession sqlSession = sqlSessionManager.openSession()) {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            user = userDao.getById(1);
            System.out.println(user);
        }

        // 命中二级缓存
        UserDao userDao1 = sqlSessionManager.getMapper(UserDao.class);
        System.out.println(userDao1.getById(1));
        Assert.assertNotNull(user);
    }

    @Test
    public void test3() throws IOException {
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionManager sqlSessionManager = SqlSessionManager.newInstance(reader);
        sqlSessionManager.startManagedSession();
        UserDao userDao = sqlSessionManager.getMapper(UserDao.class);
        User user = userDao.getById(2);

        // 命中一级缓存
        UserDao userDao2 = sqlSessionManager.getMapper(UserDao.class);
        User user2 = userDao2.getById(2);
        System.out.println(user2);
    }

}
