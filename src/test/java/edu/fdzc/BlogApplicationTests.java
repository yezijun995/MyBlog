package edu.fdzc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class BlogApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }

}
