package com.aptech.blog.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("GET /blogs/1 success")
    void testGetBlogById() throws Exception {

        // Set up mocked service
        Blog mockBLog = new Blog(1, "Title 1", "http://localhost:8080/1", 3);
        doReturn(Optional.of(mockBLog)).when(blogService).getBlogById(1);
        // Excute the GET request
        mockMvc.perform(get("/Blogs/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.blogId").value("1"))
                .andExpect(jsonPath("$.title").value("Title 1"))
                .andExpect(jsonPath("$.url").value("http://localhost:8080/1"))
                .andExpect(jsonPath("$.rating").value(3))
                .andReturn();

    }

    @Test
    void testGetBlogs() throws Exception {
        Blog mockBLog1 = new Blog(1, "Title 1", "http://localhost:8080/1", 3);
        Blog mockBLog2 = new Blog(2, "Title 2", "http://localhost:8080/2", 3);
        List<Blog> blogList = Arrays.asList(mockBLog1, mockBLog2);
        PageRequest pagination = PageRequest.of(0, 2);
        Page<Blog> pageBlog = new PageImpl<>(blogList, pagination, blogList.size());
        doReturn(pageBlog).when(blogService).getAll(0, 2);
        mockMvc.perform(get("/blogs?pageNo=0&pageSize=2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                // Validate the returned fields
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].blogId", is(1)))
                .andExpect(jsonPath("$.content[0].title", is("Title 1")))
                .andExpect(jsonPath("$.content[0].url", is("http://localhost:8080/1")))
                .andExpect(jsonPath("$.content[0].rating", is(3)))
                .andExpect(jsonPath("$.content[1].blogId", is(2)))
                .andExpect(jsonPath("$.content[1].title", is("Title 2")))
                .andExpect(jsonPath("$.content[1].url", is("http://localhost:8080/2")))
                .andExpect(jsonPath("$.content[1].rating", is(3)));

    }

    @Test
    void testUpdateBlog() {

    }
}
