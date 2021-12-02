package com.heavytiger.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();

    static {
        InputStream inputStream = null;
        Properties properties = new Properties();
        try {
            inputStream = JdbcUtils.class.getResourceAsStream("/META-INF/jdbc.properties");
            properties.load(inputStream);
            // 创建数据库连接，使用properties进行连接
            dataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(properties);
            // System.out.println(dataSource.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /* 获取数据库连接池中的对象
     * @param null
     * @return Connection 连接对象 若连接得到Connection说明获取对象成功</br>若返回null说明失败
     * @author HeavyTiger
     */

    public static Connection getConnection() {
        Connection conn = conns.get();
        if(conn == null) {
            try {
                conn = dataSource.getConnection();
                conns.set(conn);
                conn.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return conn;
    }

    /* 提交事务并关闭连接，返回数据库连接池
     * @return void
     * @author HeavyTiger
     */
    public static void commitAndClose() {
        Connection conn = conns.get();
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        conns.remove();
    }

    /* 回滚事务并关闭连接，返回数据库连接池
     * @return void
     * @author HeavyTiger
     */
    public static void rollbackAndClose() {
        Connection conn = conns.get();
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        conns.remove();
    }
}
