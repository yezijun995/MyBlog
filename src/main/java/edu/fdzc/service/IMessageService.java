package edu.fdzc.service;

import edu.fdzc.entity.Message;

import java.util.List;

public interface IMessageService {

    /**
     * 查询所有留言
     * @return
     */
     List<Message> getAllMessage();

    /**
     * 添加留言
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
