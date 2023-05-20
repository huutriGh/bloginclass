package com.aptech.blog.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aptech.blog.model.Blog;
import com.aptech.blog.service.BlogService;

import jakarta.websocket.server.PathParam;

@RestController

public class BlogController {

    @Autowired
    private BlogService service;

    // @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @GetMapping(path = "/blogs", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Page<Blog>> getBlogs(
            @RequestParam(defaultValue = "${paging.pageNo}") int pageNo,
            @RequestParam(defaultValue = "${paging.pageSize}") int pageSize) {

        return new ResponseEntity<Page<Blog>>(service.getAll(pageNo, pageSize), HttpStatus.OK);

    }

    @PostMapping(path = "/blogs/add")
    public ResponseEntity<String> addBlog(@RequestBody Blog blog) {

        try {
            service.addBlog(blog);
            return new ResponseEntity<>("Add blog success.", HttpStatus.CREATED);
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping(path = "/blogs/delete")
    public ResponseEntity<String> deleteBlog(@RequestBody Blog blog) {

        service.deleteBlog(blog);
        return new ResponseEntity<>("Delete blog success.", HttpStatus.OK);
    }

    @PutMapping(path = "/blogs/update")
    public ResponseEntity<String> updateBlog(@RequestBody Blog blog) {
        service.updateBlog(blog);
        return new ResponseEntity<>("Update blog success.", HttpStatus.OK);
    }

    @GetMapping(path = "/Blogs/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable int id) {

        Optional<Blog> blog = service.getBlogById(id);

        return new ResponseEntity<Blog>(blog.get(), HttpStatus.OK);
    }

    
    // @PostMapping(path = "/Blogs/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    // public ResponseEntity<String> upload(@RequestPart("files") MultipartFile[] files) {
    //     Path root = Paths.get("uploads");
    //     try {
    //         Files.createDirectories(root);
    //         for (MultipartFile file : files) {
    //             System.out.println(file.getOriginalFilename());
    //             Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
    //         }
    //         return new ResponseEntity<>("Upload file success.", HttpStatus.OK);
    //     } catch (IOException e) {
    //         throw new RuntimeException("Could not initialize folder for upload!");

    //     }

    // }

    // @PostMapping(path = "/Blogs/download")
    // public ResponseEntity<Resource> download(@RequestParam String fileName) throws MalformedURLException {
    //     Path root = Paths.get("uploads" + File.separator + fileName);
    //     Resource file = new UrlResource(root.toUri());
    //     return ResponseEntity.ok()
    //             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
    //             .contentType(MediaType.APPLICATION_OCTET_STREAM)
    //             .body(file);

    // }
    
     @PostMapping(path = "/Blogs/upload/{blogId}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> upload(@RequestPart("files") MultipartFile[] files, @RequestPart("category") String category, @PathVariable int blogId) {
        Path root = Paths.get("uploads" + File.separator + blogId);
        try {
            Files.createDirectories(root);
            for (MultipartFile file : files) {
                System.out.println(file.getOriginalFilename());
                Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
            }
            return new ResponseEntity<>("Upload file success.", HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");

        }

    }

    @PostMapping(path = "/Blogs/download")
    public ResponseEntity<Resource> download(@RequestParam String blogId, @RequestParam String fileName)
            throws MalformedURLException {
        Path root = Paths.get("uploads" + File.separator + blogId + File.separator + fileName);
        Resource file = new UrlResource(root.toUri());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(file);

    }

}
