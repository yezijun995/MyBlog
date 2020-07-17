package edu.fdzc.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fdzc.entity.Type;
import edu.fdzc.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private ITypeService typeService;

    /**
     * 分页查询分类列表
     * @param model
     * @param pageNum
     * @return
     */
    @GetMapping("/types")
    public String types(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        //按照排序字段 倒序排序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum, 10, orderBy);
        List<Type> types = typeService.getAllType();
        //PageInfo是一个分页的bean
        PageInfo<Type> pageInfo = new PageInfo<>(types);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }

    /**
     * 添加分页
     * @param type
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("types")
    public String addType(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        Type isType = typeService.getTypeByName(type.getName().trim());
        if (isType != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        if (typeService.saveType(type) == 0) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }

    /**
     * 删除分类
     */
    @DeleteMapping("types/{id}")
    public String deleteType(@PathVariable("id") Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }

    /**
     * 修改分页
     * @param type
     * @return
     */
    @PutMapping("types")
    public String updateType(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        Type isType = typeService.getTypeByName(type.getName().trim());
        if (isType != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        if (typeService.updateType(type) == 0) {
            attributes.addFlashAttribute("message", "修改失败");
        } else {
            attributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/types";
    }


    /**
     * 跳转新增分页页面
     * @param model
     * @return
     */
    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    /**
     * 跳转修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("types/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

}
