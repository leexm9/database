package com.leexm.demo.database.multidatabase;

import com.leexm.demo.database.entity.User;
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
 * @date 2020/7/8 14:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(locations = {"classpath:/spring/multidatabase.xml"})
public class UserManagerTest {

    @Autowired
    private UserManager userManager;

    @Test
    public void testGetById() {
        User user = userManager.getById(1);
        System.out.println(user);
        Assert.assertNotNull(user.getName());
    }

    @Test
    public void testGetByIds() {
        List<Integer> ids = Arrays.asList(new Integer[]{1, 2});
        List<User> users = userManager.getByIds(ids);
        System.out.println(users);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setName("Helen");
        user.setAge(23);
        user.setSex("female");
        Assert.assertTrue(userManager.saveUser(user));
    }

}
