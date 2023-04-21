package com.aptech.blog.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Post {

    @Id
    private int id;
    private String title;
    private String contents;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blogId", updatable = false, insertable = false)
    private Blog blog;

}
