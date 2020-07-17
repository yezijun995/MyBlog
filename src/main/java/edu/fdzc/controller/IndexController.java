package edu.fdzc.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fdzc.entity.Type;
import edu.fdzc.queryvo.FirstPageBlog;
import edu.fdzc.queryvo.RecommendBlog;
import edu.fdzc.queryvo.TypeBar;
import edu.fdzc.service.IBlogService;
import edu.fdzc.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ITypeService typeService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, RedirectAttributes attributes) {

        PageHelper.startPage(pageNum, 10);
        List<FirstPageBlog> firstPageBlogs = blogService.getAllFirstPageBlog();

        List<TypeBar> types = typeService.getTypeSortById(6L);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(firstPageBlogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", types);
        return "index";
    }

    /**
     * 搜索博客
     *
     * @param model
     * @param pageNum
     * @param query
     * @return
     */
    @RequestMapping(value = "/search", method = {RequestMethod.GET, RequestMethod.POST})
    public String search(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @RequestParam String query, HttpSession session) {
        PageHelper.startPage(pageNum, 10);
        List<FirstPageBlog> searchBlog = blogService.getSearchBlog(query);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        model.addAttribute("blog",blogService.getDetailedBlog(id));
        return "blog";
    }
}
