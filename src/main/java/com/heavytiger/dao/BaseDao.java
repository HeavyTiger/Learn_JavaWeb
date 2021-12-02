package com.heavytiger.dao;

import com.heavytiger.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    //使用dbUtils操作数据库
    private final QueryRunner queryRunner = new QueryRunner();

    /**
     * 该方法执行sql查询语句，在查询结束后，返回影响的行数
     * @param sql 查询时的sql语句
     * @param args  查询时的param参数
     * @return  若返回-1，说明查询失败，否则查询成功，返回影响的行数。
     */
    public int update(String sql, Object ... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.update(conn, sql, args);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    /**
     * 查询并返回一个javaBean的sql语句
     * @param type  返回的对象类型
     * @param sql   执行的sql语句
     * @param args  sql对应的参数值
     * @param <T>   返回类型的泛型
     * @return      若返回值为null表示未查询到，否则查询成功返回一个对应类型的javaBean对象
     */
    public <T> T queryForOne(Class<T> type, String sql, Object ... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanHandler<T>(type), args);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    /**
     * 查询并返回多个javaBean对象的sql语句
     * @param type  返回的对象类型
     * @param sql   执行的sql语句
     * @param args  sql对应的参数值
     * @param <T>   返回类型的泛型
     * @return      若返回值为null表示未查询到，否则查询成功返回一个对应类型的javaBean对象
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object ... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            ResultSetHandler<List<T>> resultHandler = new BeanListHandler<T>(type);
            return queryRunner.query(conn, sql, resultHandler, args);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    public <T> T queryForSingleValue(Class<T> type, String sql, Object ... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new ScalarHandler<T>(), args);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }
}
