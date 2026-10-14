package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.IBlogService;
import com.codegym.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    // Hiển thị danh sách blog có Phân trang, Sắp xếp giảm dần theo ngày tạo, và Tìm kiếm
    @GetMapping
    public String listBlogs(
            @RequestParam("search") Optional<String> search,
            @RequestParam("page") Optional<Integer> page,
            Model model) {

        int currentPage = page.orElse(0);
        int pageSize = 3; // Mỗi trang 3 bài viết

        // Sắp xếp bài viết mới nhất lên đầu (createdAt DESC)
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("createdAt").descending());

        Page<Blog> blogs;
        if (search.isPresent() && !search.get().trim().isEmpty()) {
            blogs = blogService.findAllByTitle(search.get(), pageable);
            model.addAttribute("search", search.get());
        } else {
            blogs = blogService.findAll(pageable);
        }

        model.addAttribute("blogs", blogs);
        model.addAttribute("categories", categoryService.findAll());
        return "blog/list";
    }

    // Hiển thị bài viết theo từng Danh mục
    @GetMapping("/category/{id}")
    public String listBlogsByCategory(
            @PathVariable("id") Long id,
            @RequestParam("page") Optional<Integer> page,
            Model model) {

        Category category = categoryService.findById(id);
        Pageable pageable = PageRequest.of(page.orElse(0), 3, Sort.by("createdAt").descending());

        Page<Blog> blogs = blogService.findAllByCategory(category, pageable);

        model.addAttribute("blogs", blogs);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("selectedCategory", category);
        return "blog/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryService.findAll());
        return "blog/create";
    }

    @PostMapping("/create")
    public String saveBlog(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/{id}")
    public String viewBlog(@PathVariable("id") Long id, Model model) {
        model.addAttribute("blog", blogService.findById(id));
        return "blog/view";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return "redirect:/blogs";
        }

        model.addAttribute("blog", blog);
        // Truyền danh sách categories sang view edit.html
        model.addAttribute("categories", categoryService.findAll());
        return "blog/edit";
    }

    @PostMapping("/update")
    public String updateBlog(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/{id}/delete")
    public String deleteBlog(@PathVariable("id") Long id) {
        blogService.remove(id);
        return "redirect:/blogs";
    }
}