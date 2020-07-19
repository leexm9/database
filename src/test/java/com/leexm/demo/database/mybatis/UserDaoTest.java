package com.leexm.demo.database.mybatis;

import com.leexm.demo.database.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author leexm
 * @date 2020/7/9 10:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(locations = {"classpath:/spring/mybatis.xml"})
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testGetById1() {
        User user = userDao.getById(1);
        Assert.assertNotNull(user.getName());
    }

    @Test
    public void testGetById2() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            User user = sqlSession.selectOne("com.leexm.demo.database.mybatis.UserDao.getById", 1);
            sqlSession.commit();
            System.out.println(user);
            Assert.assertNotNull(user);
        }
    }

    @Test
    public void testGetById3() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserDao userDao1 = sqlSession.getMapper(UserDao.class);
            User user = userDao1.getById(1);
            sqlSession.commit();
            Assert.assertNotNull(user.getName());
        }
    }

    @Test
    public void testGetByIds() {
        List<Integer> ids = Arrays.asList(new Integer[]{1, 2});
        List<User> users = userDao.getByIds(ids);
        System.out.println(users);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setName("Jackson");
        user.setAge(26);
        user.setSex("male");
        boolean flag = userDao.saveUser(user);
        System.out.println(user.getId());
        Assert.assertTrue(flag);
    }

}