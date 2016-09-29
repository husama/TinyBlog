package com.husama.util.jdbc;

import com.husama.util.jdbc.JDBCPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by husama on 16-9-19.
 */
public class JDBCUtils {

    private static JDBCPool pool = new JDBCPool();

    public static Connection getConnection(){
        try {
            Connection connection = pool.getConnection();
            return connection;
        }catch (SQLException e){
            // TODO: 16-9-29  
            e.printStackTrace();
            return null;
        }
    }

    public static Connection getConnection(boolean isAutoCommit){
        try {
            Connection connection = pool.getConnection();
            connection.setAutoCommit(isAutoCommit);
            return connection;
        }catch (SQLException e){
            // TODO: 16-9-29  
            e.printStackTrace();
            return null;
        }
    }

    public static void release(Connection conn, Statement st, ResultSet rs){
        release(rs);
        release(st);
        release(conn);

    }

    public static void release(Connection connection){
        try {
            connection.setAutoCommit(true);//reset default auto commit
            connection.close();
        }catch (SQLException e){
            // TODO: 16-9-29  
            e.printStackTrace();
        }
    }

    public static void release(AutoCloseable ac){
        if(ac != null){
            try {
                ac.close();
            }catch (Exception e){
                // TODO: 16-9-29  
                e.printStackTrace();
            }
            ac = null;
        }
    }

    public static void commit(Connection connection){
        try {
            connection.commit();
        }catch (SQLException e){
            // TODO: 16-9-29  
            e.printStackTrace();
        }
    }
}
