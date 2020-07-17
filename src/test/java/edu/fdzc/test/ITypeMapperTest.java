package edu.fdzc.test;

import edu.fdzc.mapper.ITypeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ITypeMapperTest {

    @Autowired
    private ITypeMapper typeMapper;

    @Test
    public void getTypeTop() {

        typeMapper.getTypeSortById(3L).forEach(System.out::println);
    }

    @Test
    public void getAllTypeAndBlog(){
        typeMapper.getAllTypeAndBlog().forEach(System.out::println);
    }
}