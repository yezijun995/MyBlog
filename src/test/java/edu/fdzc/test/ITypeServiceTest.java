package edu.fdzc.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fdzc.entity.Type;
import edu.fdzc.service.ITypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ITypeServiceTest {

    @Autowired
    private ITypeService typeService;

    @Test
    public void saveType() {
    }

    @Test
    public void getType() {
    }

    @Test
    public void getAllType() {
        //按照排序字段 倒序排序
        String orderBy = "id desc";
        PageHelper.startPage(1,10,orderBy);
        List<Type> types = typeService.getAllType();

        //PageInfo是一个分页的bean
        PageInfo<Type> pageInfo = new PageInfo<>(types);
        System.out.println(pageInfo);
//        System.out.println(pageInfo.getList());
//        pageInfo.getList().forEach(System.out::println);
    }

    @Test
    public void updateType() {
    }

    @Test
    public void deleteType() {
    }

    @Test
    public void getTypeByName() {
        String a = "Spring Boot   ";
        System.out.println(a.trim());
    }

    @Test
    public void getAllTypeAndBlog(){
        typeService.getAllTypeAndBlog().forEach(System.out::println);
    }
}