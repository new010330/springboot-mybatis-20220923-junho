package com.boot.mybatis20220923junho.controller.api;

import com.boot.mybatis20220923junho.domain.News;
import com.boot.mybatis20220923junho.dto.CMRespDto;
import com.boot.mybatis20220923junho.dto.NewsReadRespDto;
import com.boot.mybatis20220923junho.dto.NewsWriteReqDto;
import com.boot.mybatis20220923junho.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class NewsController {

    private final NewsRepository newsRepository;

    @PostMapping("/news")
    public ResponseEntity<?> write(NewsWriteReqDto newsWriteReqDto) {

        log.info("{}", newsWriteReqDto);

        News news = newsWriteReqDto.toEntity("김준일");
        int result = newsRepository.save(news);


        if(result == 0) {
            return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "새글 작성 실패", news));
        }

        return ResponseEntity.ok(new CMRespDto<>(1, "새 글 작성 완료", news));
    }

    @GetMapping("/news/{newsId}")
    public ResponseEntity<?> read(@PathVariable int newsId) {

        log.info("{}", newsId);

        News news = newsRepository.getNews(newsId);

        NewsReadRespDto newsReadReqDto = news.toDto();


        return ResponseEntity.ok(new CMRespDto<>(1, "게시글 불러오기 성공", newsReadReqDto));
    }
}
