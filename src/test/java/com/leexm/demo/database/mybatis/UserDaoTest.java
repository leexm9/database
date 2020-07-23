package com.leexm.demo.database.mybatis;

import com.leexm.demo.database.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Test
    public void testGetById() {
        User user = userDao.getById(1);
        Assert.assertNotNull(user.getName());
    }

    @Test
    public void testGetById2() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            User user = sqlSession.selectOne("com.leexm.demo.database.mybatis.UserDao.getById", 1);
            System.out.println(user);
            Assert.assertNotNull(user.getName());

            UserDao userDao1 = sqlSession.getMapper(UserDao.class);
            User user1 = userDao1.getById(1);
            System.out.println(user1);
            Assert.assertNotNull(user1.getName());

            sqlSession.commit();
        }
    }

    @Test
    @Transactional
    public void testGetById3() {
        User user1 = userDao.getById(1);
        // 命中一级缓存
        userDao.getById(1);
        System.out.println(user1);
    }

    @Test
    public void testGetById4() {
        User user1 = userDao.getById(1);
        // 命中二级缓存
        userDao.getById(1);
        System.out.println(user1);
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

    /* ---------------- template -------------- */

    @Test
    @Transactional
    public void testTemplate1() {
        User user1 = sqlSessionTemplate.selectOne("com.leexm.demo.database.mybatis.UserDao.getById", 1);
        System.out.println(user1);

        // 命中一级缓存
        User user2 = sqlSessionTemplate.selectOne("com.leexm.demo.database.mybatis.UserDao.getById", 1);
        System.out.println(user2);
    }

}