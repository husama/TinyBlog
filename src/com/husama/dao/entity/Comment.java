package com.husama.dao.entity;

/**
 * Created by husama on 16-9-26.
 */
public class Comment extends BaseEntity {

    private String name;

    private String email;

    private String content;

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
    public int compareTo(BaseEntity o) {
        return super.getUpdatedDate().compareTo(o.getUpdatedDate());
    }
}
