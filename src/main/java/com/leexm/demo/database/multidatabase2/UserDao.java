package com.leexm.demo.database.multidatabase2;

import com.leexm.demo.database.entity.User;
import com.leexm.demo.database.entity.UserRowMapper;
import com.leexm.demo.database.multidatabase2.aop.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author leexm
 * @date 2020/6/30 10:279
 */
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @DS(db = "salve")
    public User getById(Integer id) {
        String sql = "select id, name, age, sex from user where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, UserRowMapper.getInstance());
    }

    @DS
    public List<User> getByIds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return null;
        }
        String sql = String.format("select id, name, age, sex from user where id in (%s)",
                ids.stream().map(Objects::toString).collect(Collectors.joining(",")));
        return jdbcTemplate.query(sql, UserRowMapper.getInstance());
    }

    @DS(db = "salve")
    public boolean saveUser(User user) {
        String sql = "INSERT INTO user(name, age, sex) values (?, ?, ?)";
        int rs = jdbcTemplate.update(sql, new Object[]{user.getName(), user.getAge(), user.getSex()});
        return rs == 1;
    }

}
