package edu.fdzc.test;

import edu.fdzc.entity.User;
import edu.fdzc.mapper.IUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IUserMapperTest {

    @Autowired
    private IUserMapper userMapper;

    @Test
    public void findByUsernameAndPassword() {
        User user = userMapper.findByUsernameAndPassword("zhangsan", "e10adc3949ba59abbe56e057f20f883e");
        System.out.println(user);
    }
}