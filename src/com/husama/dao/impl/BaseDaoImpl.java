package com.husama.dao.impl;

import com.husama.dao.IBaseDao;
import com.husama.util.jdbc.JDBCUtils;
import com.husama.util.reflect.ReflectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by husama on 16-9-26.
 */
public abstract class BaseDaoImpl<T> implements IBaseDao<T>{

    protected Class<T> entityClass;
    protected String tableName;
    /*
    * ensure type of entity
    * */
    @SuppressWarnings("unchecked")
    public BaseDaoImpl(){
        Type genericSuperClass = getClass().getGenericSuperclass();
        entityClass = (Class)((ParameterizedType)genericSuperClass).getActualTypeArguments()[0];
        tableName = entityClass.getSimpleName().toLowerCase();
    }

    @Override
    public boolean contains(T t) {
        return false;
    }

    @Override
    public boolean save(T t) {
        if (t == null || contains(t)) {
            return false;
        }


        Field[] fields = ReflectUtils.getAllFields(entityClass);
        String[] fieldNames = new String[fields.length];
        StringBuilder sqlBuilder = new StringBuilder("insert into " + tableName + " (");
        Object[] objects = new Object[fields.length];

        for (int i=0; i<fields.length; i++){
            fieldNames[i] = fields[i].getName();
            sqlBuilder.append(fieldNames[i] + ",");
            objects[i] = getObjectByFieldName(t,fieldNames[i]);
        }

        //去掉最后一个,符号insert insert into table_name( , , ) values(
        sqlBuilder = new StringBuilder(sqlBuilder.substring(0, sqlBuilder.lastIndexOf(","))+") values(");

        //拼装预编译SQL语句insert insert into table_name( , , )  values(?,?,?,
        for(int j = 0; j < fields.length; j++) {
            sqlBuilder.append("?,");
        }

        //去掉SQL语句最后一个,符号insert insert into table_name( , , )  values(?,?,?);
        String sql = sqlBuilder.substring(0, sqlBuilder.lastIndexOf(",")) + ")";

        Connection connection = JDBCUtils.getConnection();

        int result = executeUpdateSQL(sql,objects);

        JDBCUtils.release(connection);

        return result==1;
    }

    @Override
    public void saveOrUpdate(T t) {
        if(contains(t)){
            update(t);
        }else {
            save(t);
        }
    }

    @Override
    public T get(long id) {
        String sql = "select * from "+tableName+" where id = "+id;

        return null;
    }

    @Override
    public boolean delete(T t) {
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public void deleteAll(Collection<T> entities) {

    }

    @Override
    public void update(T t) {
    }

    @Override
    public void executeSQL(String sql, Object[] values) {
        Connection connection = JDBCUtils.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int j = 0; j < values.length; j++) {
                preparedStatement.setObject(j+1, values[j]);
            }
            preparedStatement.execute();
        }catch (SQLException e){
            // TODO: 16-9-29
            e.printStackTrace();
        }

    }

    @Override
    public int executeUpdateSQL(String sql, Object[] values) {
        Connection connection = JDBCUtils.getConnection();
        int succeed = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int j = 0; j < values.length; j++) {
                preparedStatement.setObject(j+1, values[j]);
            }
            succeed = preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return succeed;
    }

    @Override
    public ResultSet executeQuerySQL(String sql, Object[] values){
        Connection connection = JDBCUtils.getConnection();
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int j = 0; j < values.length; j++) {
                preparedStatement.setObject(j+1, values[j]);
            }
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException e){
            // TODO: 16-9-29
            e.printStackTrace();
        }

        return resultSet;
    }

    @Override
    public T getBySQL(String sql, Object[] values) {
        List<T> resultList = getListBySQL(sql, values);
        if(resultList.size() > 0){
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public List<T> getListBySQL(String sql, Object[] values) {
        ResultSet resultSet = executeQuerySQL(sql, values);


        List<Map<String, Object>> mapList = handleResultSetToMapList(resultSet);
        List<T> result = transferMapListToBeanList(mapList);

        return result;
    }

    private String firstCharToUpperCase(String s){
        char[] charArray=s.toCharArray();
        if(charArray[0]<97 || charArray[0]>122){
            return s;
        }
        charArray[0]-=32;
        return String.valueOf(charArray);
    }

    private Object getObjectByFieldName(T t, String fieldName){
        try {
            Method method = entityClass.getMethod("get" + firstCharToUpperCase(fieldName), void.class);
            Object obj;
            if (method.getReturnType().getSimpleName().contains("Date")) {
                SimpleDateFormat sbf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                obj = sbf.format(method.invoke(t));
            }else {
                obj = method.invoke(t);
            }
            return obj;
        }catch (Exception e){
            // TODO: 16-9-29
            throw new RuntimeException(e);
        }
    }

    private List<Map<String, Object>> handleResultSetToMapList(ResultSet resultSet){
        List<Map<String, Object>> values = new ArrayList<>();
        List<String> columnLabels = getColumnLabels(resultSet);
        Map<String, Object> map = null;
        // 处理 ResultSet, 使用 while 循环
        try {
            while (resultSet.next()) {
                map = new HashMap<>();

                for (String columnLabel : columnLabels) {
                    Object value = resultSet.getObject(columnLabel);
                    map.put(columnLabel, value);
                }

                //把一条记录的一个 Map 对象放入 5 准备的 List 中
                values.add(map);
            }
        }catch (SQLException e){
            // TODO: 16-9-29
            e.printStackTrace();
        }

        return values;
    }

    private List<String> getColumnLabels(ResultSet resultSet){
        List<String> labels = new ArrayList<>();

        try {
            ResultSetMetaData rsmd = resultSet.getMetaData();
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                labels.add(rsmd.getColumnLabel(i + 1));
            }
        }catch (SQLException e){
            // TODO: 16-9-29
            e.printStackTrace();
        }


        return labels;
    }

    private List<T> transferMapListToBeanList(List<Map<String, Object>> values){
        List<T> result = new ArrayList<>();

        T bean = null;
        if (values.size() > 0) {
            for (Map<String, Object> m : values) {
                //通过反射创建一个其他类的对象
                try {
                    bean = entityClass.newInstance();
                }catch (IllegalAccessException e){
                    // TODO: 16-9-29
                    e.printStackTrace();
                }catch (InstantiationException e){
                    // TODO: 16-9-29
                    e.printStackTrace();
                }


                for (Map.Entry<String, Object> entry : m.entrySet()) {
                    String propertyName = entry.getKey();
                    Object value = entry.getValue();

                    try {
                        Field f =bean.getClass().getDeclaredField(propertyName);
                        f.setAccessible(true);
                        f.set(bean, value);
                    }catch (NoSuchFieldException e){
                        // TODO: 16-9-29
                        e.printStackTrace();
                    }catch (IllegalAccessException e){
                        // TODO: 16-9-29
                        e.printStackTrace();
                    }


                }
                //把 Object 对象放入到 list 中.
                result.add(bean);
            }
        }

        return result;

    }

}
