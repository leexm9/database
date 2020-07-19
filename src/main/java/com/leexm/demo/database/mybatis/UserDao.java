package com.leexm.demo.database.mybatis;

import com.leexm.demo.database.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author leexm
 * @date 2020/7/8 17:11
 */
public interface UserDao {

    User getById(@Param("id") Integer id);

    List<User> getByIds(@Param("ids") List<Integer> ids);

    boolean saveUser(User user);

}
