package edu.fdzc.mapper;

import edu.fdzc.entity.Comment;
import edu.fdzc.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IMessageMapper {

    /**
     * 倒序查询所有父级评论
     * @param id
     * @return
     */
    List<Message> getMessageByParentId(@Param("ParentId") Long id);
    /**
     * 查询一级回复
     * @param id
     * @return
     */
    List<Message> getMessageByChildId(@Param("id") Long id);

    Message getMessageById(Long id);

    /**
     * 查询二级以及所有子集回复
     * @param childId
     * @return
     */
    List<Message> getMessageByReplayId(@Param("childId") Long childId);

    /**
     * 新增留言
     * @param message
     * @return
     */
    int addMessage(Message message);

    /**
     * 删除留言
     * @param id
     */
    void deleteMessage(Long id);
}
