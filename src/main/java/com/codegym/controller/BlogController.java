package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    // 1. Hiển thị danh sách tóm tắt các blog
    @GetMapping
    public String listBlogs(Model model) {
        model.addAttribute("blogs", blogService.findAll());
        return "blog/list";
    }

    // 2. Mở form tạo blog mới
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog/create";
    }

    // Xử lý lưu blog mới
    @PostMapping("/create")
    public String saveBlog(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        return "redirect:/blogs";
    }

    // 3. Xem chi tiết nội dung 1 blog
    @GetMapping("/{id}")
    public String viewBlog(@PathVariable("id") Long id, Model model) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blog);
        return "blog/view";
    }

    // 4. Mở form cập nhật blog
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blog);
        return "blog/edit";
    }

    // Xử lý cập nhật blog
    @PostMapping("/update")
    public String updateBlog(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        return "redirect:/blogs";
    }

    // 5. Xóa 1 blog
    @GetMapping("/{id}/delete")
    public String deleteBlog(@PathVariable("id") Long id) {
        blogService.remove(id);
        return "redirect:/blogs";
    }
}