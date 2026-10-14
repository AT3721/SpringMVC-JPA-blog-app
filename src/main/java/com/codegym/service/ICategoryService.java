package com.codegym.service;

import com.codegym.model.Category;
import com.codegym.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category findById(Long id);
    void save(Category category);
    void remove(Long id);
}