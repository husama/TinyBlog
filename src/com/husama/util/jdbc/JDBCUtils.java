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

    public static Connection getConnection() throws SQLException{
        return pool.getConnection();
    }

    public static void release(Connection conn, Statement st, ResultSet rs){
        release(rs);
        release(st);
        release(conn);
    }

    private static void release(AutoCloseable ac){
        if(ac != null){
            try {
                ac.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            ac = null;
        }
    }
}
