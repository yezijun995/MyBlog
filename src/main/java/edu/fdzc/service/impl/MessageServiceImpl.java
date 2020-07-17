package edu.fdzc.service.impl;

import edu.fdzc.entity.Comment;
import edu.fdzc.entity.Message;
import edu.fdzc.mapper.IMessageMapper;
import edu.fdzc.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 留言业务层接口实现类
 */
@Service
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private IMessageMapper messageMapper;

    //存放迭代找出的所有子代的集合
    private List<Message> tempReplys = new ArrayList<>();

    @Transactional
    @Override
    public List<Message> getAllMessage() {
        /*查询父节点*/
        List<Message> messages = messageMapper.getMessageByParentId(Long.parseLong("-1"));
        for (Message message : messages) {
            Long id = message.getId();
            String parentNickname = message.getNickname();
            this.getSubsetReview( id, parentNickname);
            message.setReplyMessages(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return messages;
    }

    /**
     * 获取一级子集评论
     * @param id
     * @param parentNickname
     */
    private void getSubsetReview(Long id, String parentNickname) {
        //一级子集留言集合
        List<Message> childMessages = messageMapper.getMessageByChildId(id);
        //查询子留言
        combineChildren(childMessages, parentNickname);
    }

    /**
     * 遍历一级子集评论
     * @param childMessages
     * @param parentNickname1
     */
    private void combineChildren(List<Message> childMessages, String parentNickname1) {
        //判断是否有一级子评论
        if (childMessages.size() > 0) {
            //循环找出子评论的id
            for (Message childMessage : childMessages) {
                String parentNickname = childMessage.getNickname();
                childMessage.setParentNickname(parentNickname1);
                tempReplys.add(childMessage);
                Long childId = childMessage.getId();
                //查询出子二级评论
                recursively(childId, parentNickname);
            }
        }
    }

    /**
     * 寻找子集评论中的子集
     * @param childId
     * @param parentNickname1
     */
    private void recursively(Long childId, String parentNickname1) {
        //根据子一级评论的id找到子二级评论
        List<Message> replayMessages = messageMapper.getMessageByReplayId(childId);

        if (replayMessages.size() > 0) {
            for (Message replayMessage : replayMessages) {
                String parentNickname = replayMessage.getNickname();
                replayMessage.setParentNickname(parentNickname1);
                Long replayId = replayMessage.getId();
                tempReplys.add(replayMessage);
                recursively(replayId, parentNickname);
            }
        }
    }

    @Transactional
    @Override
    public int addMessage(Message message) {
        message.setCreateTime(new Date());
        return messageMapper.addMessage(message);
    }

    @Transactional
    @Override
    public void deleteMessage(Long id) {

        Message message = messageMapper.getMessageById(id);
        /*获取子集评论*/
        this.getSubsetReview(message.getId(), message.getParentNickname());
        /*删除子集评论*/
        if (tempReplys.size() > 0) {
            for(Message sonMessage:tempReplys){
                messageMapper.deleteMessage(sonMessage.getId());
            }
        }
        messageMapper.deleteMessage(id);
        /*清空集合*/
        tempReplys = new ArrayList<>();
    }
}
