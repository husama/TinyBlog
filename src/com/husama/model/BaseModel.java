package com.husama.model;

import com.husama.annotation.dao.Persistence;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by husama on 16-9-19.
 */
public abstract class BaseModel implements Comparable<BaseModel>,Serializable{

    @Persistence
    private Long id;

    @Persistence
    private Date createdDate;

    @Persistence
    private Date updatedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public int compareTo(BaseModel o) {
        return this.id.compareTo(o.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        return this.id.equals(((BaseModel)obj).getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


}
