package com.husama.dao.relationship;

/**
 * Created by husama on 16-9-26.
 */
public class ArticleCategory {

    private long articleId;

    private long CategoryId;

    public ArticleCategory() {
    }

    public ArticleCategory(long articleId, long categoryId) {
        this.articleId = articleId;
        CategoryId = categoryId;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public long getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(long categoryId) {
        CategoryId = categoryId;
    }
}
