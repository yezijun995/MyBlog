package edu.fdzc.mapper;

import edu.fdzc.entity.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ICommentMapperTest {

    @Autowired
    private ICommentMapper commentMapper;

    @Test
    public void saveComment() {
        Comment comment = new Comment();
        comment.setNickname("张三");
        comment.setEmail("zhangsan@qq.com");
        comment.setContent("zhangzhang");
        comment.setAvatar("/images/avatar.jpg");
        comment.setCreateTime(new Date());
        comment.setBlogId(87L);
        comment.setParentCommentId(-1L);
        comment.setAdminComment(false);
    }
}