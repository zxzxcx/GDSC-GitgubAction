package com.example.mytestproject.api;

import com.example.mytestproject.dto.CommentDto;
import com.example.mytestproject.entity.Comment;
import com.example.mytestproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
//    URL 설계 : RestAPI 활용
//    GET - /articles/articleId/comments
//    POST - /articles/articleId/comments
//    PATCH - /comments/id
//    DELETE - /comments/id

    @Autowired
    private CommentService commentService;

    // R 조회 //    GET - /articles/articleId/comments
    @GetMapping("/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        // 서비스 전달
        List<CommentDto> dtos = commentService.comments(articleId);
        // 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
        //예외 처리를 했다고 가정 성공한 경우만 응답 전송
    }

    // C 생성 //    POST - /articles/articleId/comments
    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                                             @RequestBody CommentDto dto ){
        // 서비스 전달
        CommentDto createDto = commentService.create(articleId, dto);
        // 결과 리턴
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }
    // U 패치,업데이트  //    PATCH - /comments/id
    @PatchMapping("/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto dto ){
        //서비스로 전달
        CommentDto updateDto = commentService.update(id,dto);
        //결과 리턴
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }

    // D 삭제  //    DELETE - /comments/id
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){
        //서비스로 전달
        CommentDto deleteData = commentService.delete(id);
        //결과 리턴
        return ResponseEntity.status(HttpStatus.OK).body(deleteData);
    }

}
