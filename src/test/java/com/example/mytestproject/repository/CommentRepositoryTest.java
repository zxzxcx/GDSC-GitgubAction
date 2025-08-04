package com.example.mytestproject.repository;

import com.example.mytestproject.entity.Article;
import com.example.mytestproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //레포지터리와 테스트 하겠다.
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정_아이디_조회하여_테스트") // 테스트
    void findByArticleId() {
        //6번 게시글을 조회하는 댓글 조회
        Long articleId = 6L;
        // 1. expected - 예상 데이터 작성
        Article article = new Article(6L, "나의 장점은?", "대댓 ㄱㄱ");
        Comment fir = new Comment(7L, article, "Rora","청소");
        Comment sec = new Comment(8L, article, "Carten","집에잘감");
        Comment thr = new Comment(9L, article, "Aiden","타자");
        List<Comment> expected = Arrays.asList(fir,sec,thr);
        // 2. 실제 데이터 수집
        List<Comment> commentList = commentRepository.findByArticleId(articleId);
        // 3. 비교
        assertEquals(expected.toString(),commentList.toString());
    }
    @Test
    @DisplayName("1번게시글_조회_테스트_무댓글") // 테스트
    void findByArticleId_null() {
        //1번 게시글을 조회하는 댓글 조회
        Long articleId = 1L;
        // 1. expected - 예상 데이터 작성
        Article article = new Article(1L, "111", "111");
        List<Comment> expected = Arrays.asList();
        // 2. 실제 데이터 수집
        List<Comment> commentList = commentRepository.findByArticleId(articleId);
        // 3. 비교
        assertEquals(expected.toString(),commentList.toString(),"1번 게시글은 댓글없음");
    }

    @Test
    @DisplayName("특정 닉네임으로 댓글 조회")
    void findByNickname() {
        String nickname="Aiden";
        //예상데이터

        Comment fir = new Comment(2L,
                new Article(4L, "나의 최애는?", "ㄱㄱㄱ"), nickname, "심형래");
        Comment sec = new Comment(4L,
                new Article(5L, "나의 인생 언어는?", "댓 ㄱㄱ"), nickname, "영어");
        Comment thr = new Comment(9L,
                new Article(6L, "나의 장점은?", "대댓 ㄱㄱ"), nickname,"타자");
        List<Comment> expected = Arrays.asList(fir,sec,thr);
        //실제 데이터
        List<Comment> commentList = commentRepository.findByNickname(nickname);
        //비교
        assertEquals(expected.toString(),commentList.toString());
    }


}