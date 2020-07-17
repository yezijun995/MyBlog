package edu.fdzc.test;

import edu.fdzc.mapper.IBlogMapper;
import edu.fdzc.queryvo.BlogQuery;
import edu.fdzc.queryvo.SearchBlog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IBlogMapperTest {

    @Autowired
    private IBlogMapper blogMapper;

    @Test
    public void getAllBlogQuery() {
        blogMapper.getAllBlogQuery().forEach(System.out::println);
    }

    @Test
    public void searchByTitleOrTypeOrRecommend(){
        SearchBlog searchBlog = new SearchBlog();
        searchBlog.setTitle("分");
        searchBlog.setTypeId(1L);
        blogMapper.searchByTitleOrTypeOrRecommend(searchBlog).forEach(System.out::println);
    }
}