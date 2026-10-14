package com.codegym.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    private LocalDateTime createdAt;

    // Mối quan hệ N-1: Nhiều bài blog thuộc về 1 Danh mục
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Blog() {
        this.createdAt = LocalDateTime.now();
    }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}