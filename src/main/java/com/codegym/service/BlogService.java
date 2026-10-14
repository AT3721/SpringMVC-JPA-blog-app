package com.codegym.service;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
class BlogService implements IBlogService {
    @Autowired
    private IBlogRepository blogRepository;

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> findAllByTitle(String title, Pageable pageable) {
        return blogRepository.findAllByTitleContainingIgnoreCase(title, pageable);
    }

    @Override
    public Page<Blog> findAllByCategory(Category category, Pageable pageable) {
        return blogRepository.findAllByCategory(category, pageable);
    }

    @Override public Blog findById(Long id) { return blogRepository.findById(id).orElse(null); }

    @Override
    public void save(Blog blog) {
        if (blog.getId() == null) blog.setCreatedAt(LocalDateTime.now());
        blogRepository.save(blog);
    }

    @Override public void remove(Long id) { blogRepository.deleteById(id); }
}