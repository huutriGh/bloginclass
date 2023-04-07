package com.aptech.blog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aptech.blog.model.Blog;
import com.aptech.blog.service.BlogService;

@RestController

@CrossOrigin("http://localhost:3000")
public class BlogController {

    @Autowired
    private BlogService service;

    @GetMapping(path = "/blogs", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Page<Blog>> getBlogs(
            @RequestParam(defaultValue = "${paging.pageNo}") int pageNo,
            @RequestParam(defaultValue = "${paging.pageSize}") int pageSize) {
        return ResponseEntity.ok().body(service.getAll(pageNo, pageSize));

    }

    @PostMapping(path = "/blogs/add", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Blog> addBlog(@RequestBody Blog blog) {

        Blog newblog = service.addBlog(blog);
        return new ResponseEntity<>(newblog, HttpStatus.CREATED);

    }

    @PostMapping(path = "/blogs/delete")
    public ResponseEntity<String> deleteBlog(@RequestBody Blog blog) {

        service.deleteBlog(blog);
        return new ResponseEntity<>("Delete blog success.", HttpStatus.OK);
    }

    @PutMapping(path = "/blogs/update")
    public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog) {
       Blog newBlog =  service.updateBlog(blog);
        return new ResponseEntity<>(newBlog, HttpStatus.OK);
    }

    @GetMapping(path = "/Blogs/{id}")
    public ResponseEntity<Optional<Blog>> getBlogById(@PathVariable int id) {

        return new ResponseEntity<Optional<Blog>>(service.getBlogById(id), HttpStatus.OK);
    }

}
