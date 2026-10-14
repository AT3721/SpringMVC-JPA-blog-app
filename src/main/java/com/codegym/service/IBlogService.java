package com.codegym.service;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

public interface IBlogService {
    Page<Blog> findAll(Pageable pageable);
    Page<Blog> findAllByTitle(String title, Pageable pageable);
    Page<Blog> findAllByCategory(Category category, Pageable pageable);
    Blog findById(Long id);
    void save(Blog blog);
    void remove(Long id);
}