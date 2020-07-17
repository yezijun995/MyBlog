package edu.fdzc.service;

import edu.fdzc.entity.Type;
import edu.fdzc.queryvo.TypeBar;

import java.util.List;

/**
 * 文章分类业务层接口
 */
public interface ITypeService {

    /**
     * 添加分类
     * @param type
     * @return
     */
    int saveType(Type type);

    /**
     * 获取指定分类
     * @param id
     * @return
     */
    Type getType(Long id);

    /**
     * 查询全部分类
     * @return
     */
    List<Type> getAllType();

    /**
     * 修改分类
     * @param type
     * @return
     */
    int updateType(Type type);

    /**
     * 删除分类
     * @param id
     */
    void deleteType(Long id);


    /**
     * 根据名字查询
     * @param name
     * @return
     */
    Type getTypeByName(String name);


    /**
     * 按数量查询分类与博客数
     * @return
     */
    List<TypeBar> getTypeSortById(Long size);

    /**
     * 查询所有分类与博客数
     * @return
     */
    List<TypeBar> getAllTypeAndBlog();
}
