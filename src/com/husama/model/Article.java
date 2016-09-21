package com.husama.model;

import com.husama.annotation.dao.ManyToMany;
import com.husama.annotation.dao.Persistence;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by husama on 16-8-28.
 */
public class Article extends BaseModel{

    public static final String FORMAT_MARKDOWN= "MARKDOWN";

    public static final String FORMAT_TEXT = "TEXT";

    @Persistence
    private String title;

    @Persistence
    private String content;//原始内容

    @Persistence
    private String renderedContent;//渲染后的内容

    @Persistence
    private String format = FORMAT_MARKDOWN;//渲染方式默认为markdown

    @ManyToMany
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany
    private Set<Comment> comments = new TreeSet<>();

    @Persistence
    private int viewCount;

    @Persistence
    private String link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRenderedContent() {
        if(this.format.equals(FORMAT_MARKDOWN)){
            return renderedContent;
        }else {
            return content;
        }
    }

    public void setRenderedContent(String renderedContent) {
        this.renderedContent = renderedContent;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
