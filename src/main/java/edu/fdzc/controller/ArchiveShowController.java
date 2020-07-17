package edu.fdzc.controller;

import edu.fdzc.queryvo.BlogQuery;
import edu.fdzc.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 归档页面控制器
 */
@Controller
public class ArchiveShowController {

    @Autowired
    private IBlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model) {
        List<BlogQuery> blogs = blogService.getAllBlogQuery();
        model.addAttribute("blogs", blogs);
        return "archives";
    }
}
