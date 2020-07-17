package edu.fdzc.mapper;

import edu.fdzc.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论持久层接口
 */
@Mapper
public interface ICommentMapper {

    /**
     * 根据创建时间倒序来排
     * @param blogId
     * @param blogParentId
     * @return
     */
    List<Comment> getCommentByParentId(@Param("blogId") Long blogId, @Param("blogParentId") Long blogParentId);

    /**
     * 查询一级回复
     * @param blogId
     * @param id
     * @return
     */
    List<Comment> getCommentByChildId(@Param("blogId") Long blogId, @Param("id") Long id);

    /**
     * 查询二级回复
     * @param blogId
     * @param childId
     * @return
     */
    List<Comment> getCommentByBlogIdAndReplayId(@Param("blogId") Long blogId,@Param("childId") Long childId);

    /**
     * 添加一个评论
     * @param comment
     * @return
     */
    int saveComment(Comment comment);

    /**
     * 删除评论
     * @param id
     */
    void deleteComment(Long id);
}
