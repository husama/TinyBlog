package com.husama.dao.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by husama on 16-9-26.
 */
public abstract class BaseEntity implements Comparable<BaseEntity>,Serializable {
    private Long id;

    private Date createdDate;

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
    public int compareTo(BaseEntity o) {
        return this.id.compareTo(o.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        return this.id.equals(((BaseEntity)obj).getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


}
