package edu.fdzc.service.impl;

import edu.fdzc.entity.Comment;
import edu.fdzc.mapper.IBlogMapper;
import edu.fdzc.mapper.ICommentMapper;
import edu.fdzc.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论业务层接口实现类
 */
@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private ICommentMapper commentMapper;
    @Autowired
    private IBlogMapper blogMapper;

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    @Transactional
    @Override
    public List<Comment> getCommentByBlogId(Long blogId) {
        /*查询父节点*/
        List<Comment> comments = commentMapper.getCommentByParentId(blogId, Long.parseLong("-1"));
        for (Comment comment : comments) {
            Long id = comment.getId();
            String parentNickname = comment.getNickname();
            this.getSubsetReview(blogId, id, parentNickname);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return comments;
    }

    /**
     * 获取一级子集评论
     *
     * @param blogId
     * @param id
     * @param parentNickname
     */
    private void getSubsetReview(Long blogId, Long id, String parentNickname) {
        //一级子集评论集合
        List<Comment> childComments = commentMapper.getCommentByChildId(blogId, id);
        //查询子评论
        combineChildren(blogId, childComments, parentNickname);
    }

    /**
     * 遍历一级子集评论
     *
     * @param blogId
     * @param childComments
     * @param parentNickname1
     */
    private void combineChildren(Long blogId, List<Comment> childComments, String parentNickname1) {
        //判断是否有一级子评论
        if (childComments.size() > 0) {
            //循环找出子评论的id
            for (Comment childComment : childComments) {
                String parentNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname1);
                tempReplys.add(childComment);
                Long childId = childComment.getId();
                //查询出子二级评论
                recursively(blogId, childId, parentNickname);
            }
        }
    }


    /**
     * 寻找子集评论中的子集
     *
     * @param blogId
     * @param childId
     * @param parentNickname1
     */
    private void recursively(Long blogId, Long childId, String parentNickname1) {
        //根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentMapper.getCommentByBlogIdAndReplayId(blogId, childId);

        if (replayComments.size() > 0) {
            for (Comment replayComment : replayComments) {
                String parentNickname = replayComment.getNickname();
                replayComment.setParentNickname(parentNickname1);
                Long replayId = replayComment.getId();
                tempReplys.add(replayComment);
                recursively(blogId, replayId, parentNickname);
            }
        }
    }


    @Transactional
    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        int comments = commentMapper.saveComment(comment);
//        文章评论计数
        return comments;
    }


    @Transactional
    @Override
    public void deleteComment(Comment comment, Long id) {
        /*获取子集评论*/
        this.getSubsetReview(comment.getBlogId(), comment.getId(), comment.getParentNickname());
        /*删除子集评论*/
        if (tempReplys.size() > 0) {
            for (Comment sonComment : tempReplys) {
                commentMapper.deleteComment(sonComment.getId());
            }
        }
        commentMapper.deleteComment(id);
        /*清空集合*/
        tempReplys = new ArrayList<>();
    }
}
