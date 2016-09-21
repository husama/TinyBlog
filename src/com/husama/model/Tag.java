package com.husama.model;

import com.husama.annotation.dao.Persistence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by husama on 16-8-29.
 */
public class Tag extends BaseModel{

    @Persistence
    private String name;

    @Persistence
    private int articleCount;

    @Persistence
    private List<Article> articles = new ArrayList<>();

    public Tag(){

    }

    public Tag(String name, int articleCount) {
        this.name = name;
        this.articleCount = articleCount;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
