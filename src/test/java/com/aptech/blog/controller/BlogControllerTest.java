package com.aptech.blog.controller;

import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.aptech.blog.model.Blog;
import com.aptech.blog.service.BlogService;

@SpringBootTest
@AutoConfigureMockMvc
public class BlogControllerTest {

    @MockBean
    private BlogService blogService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAddBlog() {

    }

    @Test
    void testDeleteBlog() {

    }

    @Test
    void testGetBlogById() {

    }

  

    @Test
    void testGetBlogs() throws Exception {

        Blog mockBLog1 = new Blog(1, "Title 1", "http://localhost:8080/1", 3);
        Blog mockBLog2 = new Blog(2, "Title 2", "http://localhost:8080/2", 4);

        List<Blog> blogList = Arrays.asList(mockBLog1,mockBLog2);
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<Blog> pageBlog = new PageImpl<>(blogList,pageRequest,blogList.size());
        doReturn(pageBlog).when(blogService).getAll(0, 2);

        mockMvc.perform(get("/blogs?pageNo=0&pageSize=2").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", hasSize(2)))

        .andExpect(jsonPath("$.content[0].blogId", is(1)))
        .andExpect(jsonPath("$.content[0].title", is("Title 1")))
        .andExpect(jsonPath("$.content[0].url", is("http://localhost:8080/1")))
        .andExpect(jsonPath("$.content[0].rating", is(3)))

        .andExpect(jsonPath("$.content[1].blogId", is(2)))
        .andExpect(jsonPath("$.content[1].title", is("Title 2")))
        .andExpect(jsonPath("$.content[1].url", is("http://localhost:8080/2")))
        .andExpect(jsonPath("$.content[1].rating", is(4)));

    }

    @Test
    void testUpdateBlog() {

    }
}
