package com.example.mytestproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity  //DB에 테이블이 생성됨
@AllArgsConstructor
@ToString
@NoArgsConstructor
//@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//DB가 id 중복안되도록 자동 생성 요청
    private Long id;
    @Column
    private String title;
    @Column
    private String content;


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void patch(Article article) {
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
    }
}
