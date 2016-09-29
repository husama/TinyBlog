package com.husama.dao.impl;

import com.husama.annotation.dao.ManyToMany;
import com.husama.annotation.dao.Persistence;
import com.husama.dao.IBaseDao;
import com.husama.model.BaseModel;
import com.husama.util.jdbc.JDBCUtils;
import com.husama.util.reflect.ReflectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * Created by husama on 16-9-19.
 */


/*
 * 一个设计错误的类，已弃用
 * */
public abstract class FalseBaseDaoImpl<T extends BaseModel> implements IBaseDao<T> {

    protected Class<T> entityClass;
    protected String tableName;

    /*
     * ensure type of entity
     * */
    @SuppressWarnings("unchecked")
    public FalseBaseDaoImpl(){
        Type genericSuperClass = getClass().getGenericSuperclass();
        entityClass = (Class)((ParameterizedType)genericSuperClass).getActualTypeArguments()[0];
        tableName = entityClass.getSimpleName().toLowerCase();

    }

    @Override
    public boolean save(T t) {

        if (t == null || contains(t)) {
            return false;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);//transaction

            List<String> persistenceFieldsName = getPersistenceFiledsName();
            StringBuilder sqlBuilder = new StringBuilder("insert into " + tableName + " (");

            int length = persistenceFieldsName.size();
            for (int i = 0; i < length - 1; i++) {

                sqlBuilder.append(persistenceFieldsName.get(i) + ", ");
            }
            sqlBuilder.append(persistenceFieldsName.get(length - 1) + ") values(");

            for (int i = 0; i < length - 1; i++) {

                sqlBuilder.append("?, ");
            }
            sqlBuilder.append("?)");

            String sql = sqlBuilder.toString();
            preparedStatement = connection.prepareStatement(sql);

            fillPreparedStatement(t, preparedStatement, persistenceFieldsName);
            preparedStatement.executeUpdate();
            JDBCUtils.release(preparedStatement);

            List<String> manyToManyFieldsName = getManyToManyFiledsName();

            saveManyToMany(t, connection, manyToManyFieldsName);

            connection.commit();
        } catch (SQLException e) {
            try {
                //operation exception , rollback
                connection.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generatedcatch block
                e1.printStackTrace();

            }

            e.printStackTrace();
        } finally {
            try {
                //reset autocommit
                connection.setAutoCommit(true);
                JDBCUtils.release(connection);

            } catch (SQLException e) {
                // TODO Auto-generatedcatch block
                e.printStackTrace();

            }
        }
        return false;
    }



    @Override
    public void update(T t) {
        if(t == null || !contains(t)){
            return;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();

            connection.setAutoCommit(false);

            List<String> persistenceFieldsName = getPersistenceFiledsName();
            persistenceFieldsName.remove("id");
            StringBuilder sqlBuilder = new StringBuilder("update "+tableName+" set ");

            int length = persistenceFieldsName.size();
            for(int i=0; i<length; i++){
                if(i < length-1){
                    sqlBuilder.append(persistenceFieldsName.get(i)+" = ?, ");
                }else {
                    sqlBuilder.append(persistenceFieldsName.get(i)+" = ? " );
                }

            }

            sqlBuilder.append("where id = "+ t.getId());

            String sql = sqlBuilder.toString();
            preparedStatement = connection.prepareStatement(sql);

            fillPreparedStatement(t, preparedStatement, persistenceFieldsName);

            preparedStatement.executeUpdate();
            JDBCUtils.release(preparedStatement);

            // TODO: 16-9-20

            List<String> manyToManyFieldsName = getManyToManyFiledsName();
            updateManyToMany(t,connection,manyToManyFieldsName);

            connection.commit();

        }catch (SQLException e) {
            try {
                //operation exception , rollback
                connection.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generatedcatch block
                e1.printStackTrace();

            }

            e.printStackTrace();
        } finally {
            try {
                //reset autocommit
                connection.setAutoCommit(true);
                JDBCUtils.release(connection);


            } catch (SQLException e) {
                // TODO Auto-generatedcatch block
                e.printStackTrace();

            }
        }
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
        return null;
    }

    @Override
    public boolean contains(T t) {
        return false;
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
    public void executeSQL(String sql, Object[] values) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            int i =1;
            for(Object o : values){
                preparedStatement.setObject(i,o);
            }
            preparedStatement.execute();
            JDBCUtils.release(preparedStatement);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
                JDBCUtils.release(connection);
        }
    }

    @Override
    public int executeUpdateSQL(String sql, Object[] values) {
        return 0;
    }

    @Override
    public ResultSet executeQuerySQL(String sql, Object[] values){
        return null;
    }

    @Override
    public T getBySQL(String sql, Object[] values) {

        return null;
    }

    @Override
    public List<T> getListBySQL(String sql, Object[] values) {
        List<T> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            int i =1;
            for(Object o : values){
                preparedStatement.setObject(i,o);
            }
            resultSet = preparedStatement.executeQuery();

            // TODO: 16-9-20
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(connection);
        }
        return null;
    }

    private List<String> getPersistenceFiledsName(){

        List<String> persistenceFileds = new ArrayList<>();

        for(Field field : ReflectUtils.getAllFields(entityClass)){
            Annotation[] annotations = field.getDeclaredAnnotations();
            for(Annotation annotation : annotations){
                if(annotation instanceof Persistence){
                    persistenceFileds.add(field.getName());
                    break;
                }
            }
        }

        return Collections.unmodifiableList(persistenceFileds);
    }

    private List<String> getManyToManyFiledsName(){
        List<String> manyToManyFileds = new ArrayList<>();

        for(Field field : ReflectUtils.getAllFields(entityClass)){
            Annotation[] annotations = field.getDeclaredAnnotations();
            for(Annotation annotation : annotations){
                if(annotation instanceof ManyToMany){
                    manyToManyFileds.add(field.getName());
                    break;
                }
            }
        }

        return Collections.unmodifiableList(manyToManyFileds);
    }

    private String firstCharToUpperCase(String s){
        char[] charArray=s.toCharArray();
        if(charArray[0]<97 || charArray[0]>122){
            return s;
        }
        charArray[0]-=32;
        return String.valueOf(charArray);
    }

    private void saveManyToMany(T t, Connection connection, List<String> manyToManyFieldsName) {
        int length;
        String sql;
        length = manyToManyFieldsName.size();
        for (String fieldName : manyToManyFieldsName) {
            try {
                Method method = entityClass.getMethod("get"+fieldName,void.class);
                Collection<BaseModel> collection = (Collection<BaseModel>)method.invoke(t);
                for(BaseModel model : collection){
                    sql = "insert into " + tableName + "_"
                            + fieldName.substring(0,fieldName.length()-1)
                            + " ("+t.getClass().getSimpleName()+"_id,"+fieldName+"_id) "+"values ("+t.getId()+","+model.getId();
                    Statement statement = connection.createStatement();
                    statement.execute(sql);
                    JDBCUtils.release(statement);
                }

            }catch (Exception e){
                // TODO: 16-9-20
                e.printStackTrace();
            }
        }
    }

    private void updateManyToMany(T t, Connection connection, List<String> manyToManyFieldsName){


    }
    private void fillPreparedStatement(T t, PreparedStatement preparedStatement, List<String> fieldNames) {
        int length = fieldNames.size();
        for(int i=0; i<length; i++) {
            try {
                Method method = entityClass.getMethod("get" + firstCharToUpperCase(fieldNames.get(i)), void.class);
                preparedStatement.setObject(i+1, method.invoke(t));
            }catch (Exception e){
                // TODO: 16-9-20
                e.printStackTrace();
            }
        }
    }
}
