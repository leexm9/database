package com.leexm.demo.database.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.leexm.demo.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author leexm
 * @date 2020/6/30 10:27
 */
public class UserDao {

    @Autowired
    private DruidDataSource dataSource;

    public User getById(Integer id) {
        String sql = "select id, name, age, sex from user where id = ?";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setSex(rs.getString("sex"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getByIds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return null;
        }
        String sql = String.format("select id, name, age, sex from user where id in (%s)",
                ids.stream().map(Objects::toString).collect(Collectors.joining(",")));
        List<User> users = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setSex(rs.getString("sex"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean saveUser(User user) {
        String sql = "INSERT INTO user(name, age, sex) values (?, ?, ?)";
        int rs = 0;
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, user.getName());
            ptmt.setInt(2, user.getAge());
            ptmt.setString(3, user.getSex());
            rs = ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs == 1;
    }

}
