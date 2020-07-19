package com.leexm.demo.database.multidatabase;

import com.leexm.demo.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author leexm
 * @date 2020/6/30 10:279
 */
public class UserManager {

    @Autowired
    private UserDao userDao;

    public User getById(Integer id) {
        return userDao.getById(id, null);
    }

    public List<User> getByIds(List<Integer> ids) {
        return userDao.getByIds(ids, null);
    }

    public boolean saveUser(User user) {
        return userDao.saveUser(user, null);
    }

}
