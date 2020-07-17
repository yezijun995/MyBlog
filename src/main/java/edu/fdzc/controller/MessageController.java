package edu.fdzc.controller;

import edu.fdzc.entity.Message;
import edu.fdzc.entity.User;
import edu.fdzc.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 留言板显示控制器
 */
@Controller
public class MessageController {

    @Autowired
    private IMessageService messageService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/message")
    public String message(Model model) {
        return "message_board";
    }

    @GetMapping("/messagecomment")
    public String messages(Model model) {
        List<Message> messages = messageService.getAllMessage();
        model.addAttribute("messages", messages);
        return "message_board::messageList";
    }

    /**
     * 添加评论
     * @param message
     * @param session
     * @param model
     * @return
     */
    @PostMapping("/message")
    public String addMessage(Message message, HttpSession session, Model model) {

        /*是否是管理员*/
        User user = (User) session.getAttribute("user");
        if (user != null) {
            message.setAvatar(user.getAvatar());
            message.setAdminMessage(true);
        } else {
            //设置头像
            message.setAvatar(avatar);
        }
        /*判断是否是子留言*/
        if (message.getParentMessage().getId() != null) {
            message.setParentMessageId(message.getParentMessage().getId());
        }
        /*添加留言*/
        messageService.addMessage(message);
        List<Message> messages = messageService.getAllMessage();
        model.addAttribute("messages", messages);
        return "message_board::messageList";
    }

    /**
     * 删除留言
     * @param id
     * @return
     */
    @GetMapping("/message/{id}/delete")
    public String deleteMessage(@PathVariable Long id){
        messageService.deleteMessage(id);
        return "redirect:/message";
    }
}
