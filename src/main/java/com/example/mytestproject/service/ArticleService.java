package com.example.mytestproject.service;

import com.example.mytestproject.api.RestApiController;
import com.example.mytestproject.dto.ArticleForm;
import com.example.mytestproject.entity.Article;
import com.example.mytestproject.repository.ArticleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private Logger log = LogManager.getLogger(RestApiController.class);
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article articles(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article entity = dto.toEntity();

        if(entity.getId() != null){
            return null;
        }

        return articleRepository.save(entity);
    }

    public Article patch(Long id, ArticleForm dto) {
        Article article = dto.toEntity();
        log.info(" id : {}, article: {}",id, article.toString());

        //db에 해당 인덱스 게시글 존재 확인
        Article dbData = articleRepository.findById(id).orElse(null);

        //만약 id가 잘못되었을 경우 처리
        //없거나 id가 일치하지 않는경우
        if(dbData==null || id != article.getId()){
            log.info("잘못된 요청, id : {}, article: {}",id, article.toString());
            return null;
        }

        //수정해야할 사항만 업데이트 patch함수 작성
        dbData.patch(article);

        Article saveData = articleRepository.save(dbData);
        return saveData;
    }

    public Article delete(Long id) {
        //삭제할 데이터가 DB에 존재하는지 체크
        Article dbData = articleRepository.findById(id).orElse(null);

        //만약에 요청이 잘못되었을 경우의 처리
        if(dbData == null){
            return null;
        }

        //삭제 완료 - 정상 삭제되었을 경우 200
        articleRepository.delete(dbData);
        return dbData;
    }

    @Transactional
    public List<Article> createTransaction(List<ArticleForm> dtos) {
        // dtos 를 엔티티 묶음으로 변환
//        List<Article> articles = new ArrayList<>();
//        for(int i=0; i < dtos.size(); i++){
//            ArticleForm dto = dtos.get(i);
//            Article entity = dto.toEntity();
//            articles.add(entity);
//        }
        //스트림 방식
        List<Article> articleStream = dtos.stream()
                .map( dto -> dto.toEntity())
                .collect(Collectors.toList());
        // DB 저장
        articleStream.stream()
                .forEach(article -> articleRepository.save(article));
        
        // 강제로 에러 발생
        //findById 로 -1인 데이터 찾기 -> 당연히 없다.
        // => orElseThrow() - 에러를 발생 (illegalArgumentException)
        articleRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("송금실패"));

        // 결과 리턴
        return articleStream;
    }
}
