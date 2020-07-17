package edu.fdzc.test;

import edu.fdzc.entity.Message;
import edu.fdzc.service.IMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IMessageServiceTest {

    @Autowired
    private IMessageService messageService;

    @Test
    public void getAllMessage() {
        messageService.getAllMessage().forEach(System.out::println);
    }

    @Test
    public void addMessage() {
        Message message = new Message();
        messageService.addMessage(message);
    }

    @Test
    public void deleteMessage() {
        messageService.deleteMessage(3L);
    }
}