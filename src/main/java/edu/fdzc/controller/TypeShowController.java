package edu.fdzc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fdzc.queryvo.FirstPageBlog;
import edu.fdzc.queryvo.RecommendBlog;
import edu.fdzc.queryvo.TypeBar;
import edu.fdzc.service.IBlogService;
import edu.fdzc.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {


    @Autowired
    private IBlogService blogService;

    @Autowired
    private ITypeService typeService;

    /**
     * 分页查询分类
     * @param id
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model) {
        List<TypeBar> typeBars = typeService.getAllTypeAndBlog();
        if (id == -1) {
            id = typeBars.get(0).getTypeId();
        }
        model.addAttribute("types", typeBars);
        List<FirstPageBlog> blogs = blogService.getByTypeId(id);
        List<RecommendBlog> recommendBlogs = blogService.getAllRecommendBlog();

        PageHelper.startPage(pageNum, 1000);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("recommendBlogs", recommendBlogs);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
