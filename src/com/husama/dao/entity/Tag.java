package com.husama.dao.entity;

/**
 * Created by husama on 16-9-26.
 */
public class Tag extends BaseEntity{

    private String name;

    private int articleCount;

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

}
