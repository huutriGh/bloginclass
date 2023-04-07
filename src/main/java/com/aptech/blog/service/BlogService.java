package com.aptech.blog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aptech.blog.model.Blog;
import com.aptech.blog.repository.BlogRepository;

@Service
public class BlogService {

    @Autowired
    private BlogRepository repository;

    public Page<Blog> getAll(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return repository.findAll(pageable);

    }

    public Optional<Blog> getBlogById(int id) {
        return repository.findById(id);
    }

    public Blog addBlog(Blog blog) {
        return repository.save(blog);

    }

    public Blog updateBlog(Blog blog) {

        return repository.save(blog);
    }

    public boolean deleteBlog(Blog blog) {
        repository.delete(blog);
        return true;

    }

}
