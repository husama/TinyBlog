package com.husama.dao.relationship;

/**
 * Created by husama on 16-9-26.
 */
public class ArticleTag {

    private long articleId;

    private long tagId;

    public ArticleTag() {
    }

    public ArticleTag(long articleId, long tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }
}
