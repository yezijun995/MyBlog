package edu.fdzc.test;

import edu.fdzc.service.IBlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IBlogServiceTest {

    @Autowired
    private IBlogService blogService;

    @Test
    public void getAllBlogQuery(){
        blogService.getAllBlogQuery().forEach(System.out::println);
    }
}
