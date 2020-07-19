package com.leexm.demo.database.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author leexm
 * @date 2020/7/7 14:34
 */
public class UserRowMapper implements RowMapper<User> {

    private UserRowMapper() {
    }

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setAge(resultSet.getInt("age"));
        user.setSex(resultSet.getString("sex"));
        return user;
    }

    public static UserRowMapper getInstance() {
        return RowMapperHolder.userRowMapper;
    }

    private static class RowMapperHolder {
        private static final UserRowMapper userRowMapper = new UserRowMapper();
    }

}
