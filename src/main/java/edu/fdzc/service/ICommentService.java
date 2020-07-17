package edu.fdzc.service;

import edu.fdzc.entity.Comment;

import java.util.List;

/**
 * 博客评论业务层接口
 */
public interface ICommentService {


    /**
     * 查询评论
     * @param blogId
     * @return
     */
    List<Comment> getCommentByBlogId(Long blogId);

    /**
     * 添加评论
     * @param comment
     * @return
     */
    int saveComment(Comment comment);

    /**
     * 删除评论
     * @param comment
     * @param id
     */
    void deleteComment(Comment comment,Long id);
}
