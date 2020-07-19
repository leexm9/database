package com.leexm.demo.database.jdbc;

import com.leexm.demo.database.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author leexm
 * @date 2020/6/30 10:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(locations = {"classpath:/spring/jdbc.xml"})
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testGetById() {
        User user = userDao.getById(1);
        Assert.assertNotNull(user.getName());
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
        user.setName("Tom");
        user.setAge(25);
        user.setSex("male");
        Assert.assertTrue(userDao.saveUser(user));
    }

}
