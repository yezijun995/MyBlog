package edu.fdzc.service.impl;

import edu.fdzc.entity.Blog;
import edu.fdzc.excption.NotFoundException;
import edu.fdzc.mapper.IBlogMapper;
import edu.fdzc.queryvo.*;
import edu.fdzc.service.IBlogService;
import edu.fdzc.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 博客列表业务层接口
 */
@Service
public class BlogServiceImpl implements IBlogService {

    @Autowired
    private IBlogMapper blogMapper;

    @Transactional
    @Override
    public ShowBlog getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }



    @Transactional
    @Override
    public List<BlogQuery> getAllBlogQuery() {
        return blogMapper.getAllBlogQuery();
    }

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        return blogMapper.saveBlog(blog);
    }

    @Transactional
    @Override
    public int updateBlog(ShowBlog showBlog) {
        showBlog.setUpdateTime(new Date());
        return blogMapper.updateBlog(showBlog);
    }


    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogMapper.deleteBlog(id);
    }

    @Transactional
    @Override
    public List<BlogQuery> getBlogBySearch(SearchBlog searchBlog) {
        return blogMapper.searchByTitleOrTypeOrRecommend(searchBlog);
    }

    @Transactional
    @Override
    public List<FirstPageBlog> getAllFirstPageBlog() {
        return blogMapper.getFirstPageBlog();
    }

    @Transactional
    @Override
    public List<RecommendBlog> getAllRecommendBlog() {
        return blogMapper.getAllRecommendBlog();
    }

    @Transactional
    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
        return blogMapper.getSearchBlog(query);
    }

    @Transactional
    @Override
    public DetailedBlog getDetailedBlog(Long id) {
        DetailedBlog detailedBlog = blogMapper.getDetailedBlog(id);
        if(detailedBlog== null){
            throw  new NotFoundException("该博客不存在");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        blogMapper.updateViews(id);
        return detailedBlog;
    }

    @Transactional
    @Override
    public List<FirstPageBlog> getByTypeId(Long typeId) {
        return blogMapper.getByTypeId(typeId);
    }
}
