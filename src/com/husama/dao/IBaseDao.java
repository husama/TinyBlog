package com.husama.dao;

import com.husama.model.BaseModel;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by husama on 16-9-19.
 */
public interface IBaseDao<T extends BaseModel> {
    void save(T t);
    void saveOrUpdate(T t);
    T get(long id);
    boolean contains(T t);
    void delete(T t);
    boolean deleteById(long id);
    void deleteAll(Collection<T> entities);
    void update(T t);
    void excuteSQL(String sql, Object[] values);
    T getBySQL(String sql, Object[] values);
    List<T> getListBySQL(String sql, Object[] values);
}
