package com.husama.dao.relationship;

/**
 * Created by husama on 16-9-26.
 */
public class ArticleComment {

    private long articleId;

    private long commentId;

    public ArticleComment() {
    }

    public ArticleComment(long articleId, long commentId) {
        this.articleId = articleId;
        this.commentId = commentId;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }
}
