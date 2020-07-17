package edu.fdzc.mapper;

import edu.fdzc.entity.Blog;
import edu.fdzc.queryvo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IBlogMapper {

    ShowBlog getBlogById(Long id);

    /**
     * 添加博客
     *
     * @param blog
     * @return
     */
    int saveBlog(Blog blog);

    /**
     * 删除博客
     *
     * @param id
     */
    void deleteBlog(Long id);

    /**
     * 修改博客
     *
     * @param showBlog
     * @return
     */
    int updateBlog(ShowBlog showBlog);

    /**
     * 后台查询所有博客
     *
     * @return
     */
    List<BlogQuery> getAllBlogQuery();

    /**
     * 根据标题或分类或推荐查询
     *
     * @param searchBlog
     * @return
     */
    List<BlogQuery> searchByTitleOrTypeOrRecommend(SearchBlog searchBlog);

    /**
     * 首页数据
     *
     * @return
     */
    List<FirstPageBlog> getFirstPageBlog();

    /**
     * 推荐文章
     *
     * @return
     */
    List<RecommendBlog> getAllRecommendBlog();

    /**
     * 搜索博客
     *
     * @param query
     * @return
     */
    List<FirstPageBlog> getSearchBlog(String query);

    /**
     * 按id查询博客详情
     * @param id
     * @return
     */
    DetailedBlog getDetailedBlog(Long id);

    /**
     * 访问数自增
     * @param id
     * @return
     */
    int updateViews(Long id);

    /**
     * 根据分类id查询博客
     * @param typeId
     * @return
     */
    List<FirstPageBlog> getByTypeId(Long typeId);
}
