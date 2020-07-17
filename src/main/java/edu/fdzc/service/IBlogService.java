package edu.fdzc.service;

import edu.fdzc.entity.Blog;
import edu.fdzc.queryvo.*;

import java.util.List;

/**
 * 博客管理业务逻辑层
 */
public interface IBlogService {

    /**
     * 查询指定博客
     * @param id
     * @return
     */
    ShowBlog getBlogById(Long id);

    /**
     * 查询全部博客
     * @return
     */
    List<BlogQuery> getAllBlogQuery();

    /**
     * 新增博客
     * @param blog
     * @return
     */
    int saveBlog(Blog blog);

    /**
     * 修改博客
     * @param showBlog
     * @return
     */
    int updateBlog(ShowBlog showBlog);

    /**
     * 删除博客
     * @param id
     */
    void deleteBlog(Long id);

    /**
     * 按照搜索数据查询
     * @return
     */
    List<BlogQuery> getBlogBySearch(SearchBlog searchBlog);

    /**
     * 获取首页数据
     * @return
     */
    List<FirstPageBlog> getAllFirstPageBlog();

    /**
     * 推荐文章数据
     * @return
     */
    List<RecommendBlog> getAllRecommendBlog();

    List<FirstPageBlog> getSearchBlog(String query);

    /**
     * 根据id查询博客
     * @param id
     * @return
     */
    DetailedBlog getDetailedBlog(Long id);

    /**
     * 根据分类id查询博客
     * @param typeId
     * @return
     */
    List<FirstPageBlog> getByTypeId(Long typeId);
}
