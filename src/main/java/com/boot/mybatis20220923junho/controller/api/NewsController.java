package com.boot.mybatis20220923junho.controller.api;

import com.boot.mybatis20220923junho.domain.News;
import com.boot.mybatis20220923junho.dto.CMRespDto;
import com.boot.mybatis20220923junho.dto.NewsWriteReqDto;
import com.boot.mybatis20220923junho.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class NewsController {

    private final NewsRepository newsRepository;

    @PostMapping("/news")
    public ResponseEntity<?> write(NewsWriteReqDto newsWriteReqDto) {

        log.info("{}", newsWriteReqDto);

        News news = newsWriteReqDto.toEntity();
        news.setNews_writer("김준일");

        newsRepository.save(news);

        return ResponseEntity.ok(new CMRespDto<>(1, "새 글 작성 완료", null));
    }
}
