package com.husama.model;

import com.husama.annotation.dao.Persistence;


/**
 * Created by husama on 16-8-29.
 */
public class Comment extends BaseModel{

    @Persistence
    private String name;

    @Persistence
    private String email;

    @Persistence
    private String content;

    @Persistence
    private String url;

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getContent(){
        return content;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int compareTo(BaseModel o) {
        return super.getUpdatedDate().compareTo(o.getUpdatedDate());
    }

}
