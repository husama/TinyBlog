package com.husama.dao.entity;

/**
 * Created by husama on 16-9-26.
 */
public class Category extends BaseEntity {

    private String categoryName;

    private int categoryCount;

    public Category(){

    }

    public Category(String categoryName, int categoryCount) {
        this.categoryName = categoryName;
        this.categoryCount = categoryCount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryCount(int categoryCount) {
        this.categoryCount = categoryCount;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


}
