package com.example.mytestproject.dto;

import com.example.mytestproject.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Getter
public class CommentDto {
    private Long id; //아이디 PK
    @JsonProperty("article_id")
    private Long articleId; //게시글 아이디 FK
    private String nickname; //댓글 작성자
    private String body; //댓글 내용

    public CommentDto(Long id, Long articleId, String nickname, String body) {
        this.id = id;
        this.articleId = articleId;
        this.nickname = nickname;
        this.body = body;
    }

    public static CommentDto createCommentDto(Comment c) {
        return new CommentDto(
                c.getId(),
                c.getArticle().getId(),
                c.getNickname(),
                c.getBody()
        );
    }

}
