package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "category/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/create";
    }

    @PostMapping("/create")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("category", categoryService.findById(id));
        return "category/edit";
    }

    @PostMapping("/update")
    public String updateCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/{id}/delete")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.remove(id);
        return "redirect:/categories";
    }
}