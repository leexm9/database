package com.leexm.demo.database.springjdbc;

import com.leexm.demo.database.entity.User;
import com.leexm.demo.database.entity.UserRowMapper;
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

    public User getById(Integer id) {
        String sql = "select id, name, age, sex from user where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, UserRowMapper.getInstance());
    }

    public List<User> getByIds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return null;
        }
        String sql = String.format("select id, name, age, sex from user where id in (%s)",
                ids.stream().map(Objects::toString).collect(Collectors.joining(",")));
        return jdbcTemplate.query(sql, UserRowMapper.getInstance());
    }

    public boolean saveUser(User user) {
        String sql = "INSERT INTO user(name, age, sex) values (?, ?, ?)";
        int rs = jdbcTemplate.update(sql, new Object[]{user.getName(), user.getAge(), user.getSex()});
        return rs == 1;
    }

}
