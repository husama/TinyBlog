package com.husama.util.jdbc;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.logging.Logger;

/**
 * Created by husama on 16-9-19.
 */


//a simple database connection pool which can not change pool's size
public class JDBCPool implements DataSource{

    private static ConcurrentSkipListSet<Connection> connections = new ConcurrentSkipListSet<>();


    //static initialize database connection pool
    static {
        Properties dbConfig = new Properties();
        InputStream in = JDBCPool.class.getClassLoader().getResourceAsStream("/web/WEB-INF/config/jdbc.properties");
        try {
            dbConfig.load(in);

            //load database connection pool config
            String jdbcDriver = dbConfig.getProperty("jdbcDriver");
            String url = dbConfig.getProperty("url");
            String user = dbConfig.getProperty("user");
            String password = dbConfig.getProperty("password");
            int jdbcPoolInitSize = Integer.parseInt(dbConfig.getProperty("jdbcPoolInitSize"));

            //load database driver
            Class.forName(jdbcDriver);

            for(int i=0; i<jdbcPoolInitSize; i++){
                connections.add(DriverManager.getConnection(url,user,password));
            }

        }catch (Exception e){
            // TODO: 16-9-29  
            throw new ExceptionInInitializerError(e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        if(connections.size() > 0){

            final Connection connction = connections.pollFirst();

            return (Connection) Proxy.newProxyInstance(JDBCPool.class.getClassLoader(),
                    connction.getClass().getInterfaces(), new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if(!method.getName().equals("close")){
                                return method.invoke(connction,args);
                            }else {
                                connections.add(connction);
                                return null;
                            }
                        }
                    });

        }else {
            throw new RuntimeException("Database busy.");
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        // TODO: 16-9-19
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        // TODO: 16-9-19
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        // TODO: 16-9-19
        return null;
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        // TODO: 16-9-19
        return 0;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        // TODO: 16-9-19
        return null;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        // TODO: 16-9-19
        return null;
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        // TODO: 16-9-19
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        // TODO: 16-9-19
    }
}
