package com.leexm.demo.database.jdbc;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author leexm
 * @date 2020/6/30 9:47
 */
public class DataSource {

    private String url;

    private String drivceName;

    private String username;

    private String password;

    private Connection connection;

    public DataSource() {
    }

    @PostConstruct
    public void init() throws ClassNotFoundException, SQLException {
        //1.加载驱动程序
        Class.forName(drivceName);
        //2. 获得数据库连接
        connection = DriverManager.getConnection(url, username, password);
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDrivceName(String drivceName) {
        this.drivceName = drivceName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
