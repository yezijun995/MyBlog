package edu.fdzc.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fdzc.entity.Blog;
import edu.fdzc.entity.Type;
import edu.fdzc.entity.User;
import edu.fdzc.queryvo.BlogQuery;
import edu.fdzc.queryvo.SearchBlog;
import edu.fdzc.queryvo.ShowBlog;
import edu.fdzc.service.IBlogService;
import edu.fdzc.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ITypeService typeService;

    /**
     * 博客列表查询
     *
     * @param model
     * @param pageNum
     * @return
     */
    @GetMapping("/blogs")
    public String list(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        //按照排序字段 倒序排序
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum, 10, orderBy);
        List<BlogQuery> blogs = blogService.getAllBlogQuery();
        //PageInfo是一个分页的bean
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", typeService.getAllType());
        return "admin/blogs";
    }

    /**
     * 跳转博客页面
     *
     * @param model
     * @return
     */
    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }

    /**
     * 博客新增
     *
     * @param blog
     * @return
     */
    @PostMapping("/blogs")
    public String addBlog(Blog blog, RedirectAttributes attributes, HttpSession session) {
        //从session中获取当前对象
        blog.setUser((User) session.getAttribute("user"));
        //获取当前分类并设置
        blog.setType(typeService.getType(blog.getType().getId()));
        //设置blog中对应的type属性
        blog.setTypeId(blog.getType().getId());
        //设置blog中对应的user属性
        blog.setUserId(blog.getUser().getId());

        int isBlog = blogService.saveBlog(blog);

        if (isBlog == 0) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/blogs";
    }

    /**
     * 删除博客
     *
     * @param id
     * @param attributes
     * @return
     */
    @DeleteMapping("/blogs/{id}")
    public String deleteBlog(@PathVariable("id") Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }

    /**
     * 跳转修改页面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        ShowBlog blog = blogService.getBlogById(id);
        List<Type> types = typeService.getAllType();
        model.addAttribute("blog", blog);
        model.addAttribute("types", types);
        System.out.println(blog);
        System.out.println(types);
        return "admin/blogs-input";
    }

    /**
     * 修改博客文章
     *
     * @param blog
     * @param attributes
     * @return
     */
    @PutMapping("/blogs")
    public String updateBlog(@Valid ShowBlog blog, RedirectAttributes attributes) {
        int isBlog = blogService.updateBlog(blog);
        if (isBlog == 0) {
            attributes.addFlashAttribute("message", "修改失败");
        } else {
            attributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/blogs";
    }

    /**
     * 搜索
     *
     * @return
     */
    @PostMapping("/blogs/search")
    public String searchBlog(SearchBlog searchBlog, Model model, @RequestParam(defaultValue = "1", value = "page") Integer pageNum) {
        String orderBy = "update_time desc";
        System.out.println(pageNum);
        PageHelper.startPage(pageNum, 10, orderBy);
        List<BlogQuery> blogBySearch = blogService.getBlogBySearch(searchBlog);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogBySearch);
        System.out.println(pageInfo);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("search",true);
        return "admin/blogs :: blogList";
    }

}
