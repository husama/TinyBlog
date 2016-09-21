package com.husama.model;

import com.husama.annotation.dao.ManyToMany;
import com.husama.annotation.dao.Persistence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by husama on 16-8-29.
 */
public class Category extends BaseModel{

    @Persistence
    private String categoryName;

    @Persistence
    private int categoryCount;

    @ManyToMany
    private List<Article> articles = new ArrayList<>();

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

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
