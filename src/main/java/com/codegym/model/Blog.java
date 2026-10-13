package com.codegym.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;       // Tiêu đề
    private String author;      // Tác giả

    @Column(columnDefinition = "TEXT")
    private String summary;     // Tóm tắt nội dung

    @Column(columnDefinition = "LONGTEXT")
    private String content;     // Nội dung chi tiết

    private LocalDateTime createdAt; // Ngày tạo

    public Blog() {
        this.createdAt = LocalDateTime.now();
    }

    public Blog(String title, String author, String summary, String content) {
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    // Getters & Setters
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