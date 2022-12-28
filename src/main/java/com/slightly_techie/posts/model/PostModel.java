package com.slightly_techie.posts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostModel {
        private String title;
        private String author;
        private String content;

}
