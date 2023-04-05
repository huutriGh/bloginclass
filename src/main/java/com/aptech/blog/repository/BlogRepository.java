package com.aptech.blog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.aptech.blog.model.Blog;

public interface BlogRepository extends PagingAndSortingRepository<Blog, Integer>,
        CrudRepository<Blog, Integer>{    
   
}
