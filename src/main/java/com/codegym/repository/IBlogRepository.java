package com.codegym.repository;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlogRepository extends JpaRepository<Blog, Long> {

    // Tìm kiếm bài viết theo tiêu đề (Không phân biệt hoa thường) + Phân trang
    Page<Blog> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);

    // Lấy bài viết theo Danh mục + Phân trang
    Page<Blog> findAllByCategory(Category category, Pageable pageable);
}