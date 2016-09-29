package com.husama.dao;

import com.husama.model.BaseModel;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;

/**
 * Created by husama on 16-9-19.
 */
public interface IBaseDao<T> {
    boolean save(T t);
    void saveOrUpdate(T t);
    T get(long id);
    boolean contains(T t);
    boolean delete(T t);
    boolean deleteById(long id);
    void deleteAll(Collection<T> entities);
    void update(T t);
    void executeSQL(String sql, Object[] values);
    int executeUpdateSQL(String sql, Object[] values);
    ResultSet executeQuerySQL(String sql, Object[] values);
    T getBySQL(String sql, Object[] values);
    List<T> getListBySQL(String sql, Object[] values);
}
