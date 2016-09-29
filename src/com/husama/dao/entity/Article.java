package com.husama.dao.entity;

import static com.husama.constant.FormatType.*;

/**
 * Created by husama on 16-9-26.
 */
public class Article extends BaseEntity{


    private String title;

    private String content;//原始内容

    private String renderedContent;//渲染后的内容

    private String format = FORMAT_MARKDOWN;//渲染方式默认为markdown

    private int viewCount;

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
