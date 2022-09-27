package com.boot.mybatis20220923junho.controller.api;

import com.boot.mybatis20220923junho.domain.News;
import com.boot.mybatis20220923junho.domain.NewsFile;
import com.boot.mybatis20220923junho.dto.CMRespDto;
import com.boot.mybatis20220923junho.dto.NewsReadRespDto;
import com.boot.mybatis20220923junho.dto.NewsWriteReqDto;
import com.boot.mybatis20220923junho.dto.NewsWriteRespDto;
import com.boot.mybatis20220923junho.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class NewsController {


    @Value("${file.path}")
    private String filePath;
    private final NewsRepository newsRepository;

    @PostMapping("/news")
    public ResponseEntity<?> write(NewsWriteReqDto newsWriteReqDto) {

        log.info("{}", newsWriteReqDto);

        List<NewsFile> newsFileList = null;

        MultipartFile firstFile = newsWriteReqDto.getFiles().get(0); // Dto에서 file 리스트의 0번째 인덱스를 호출한다.
        String firstFileName = firstFile.getOriginalFilename(); // 0번째 인덱스의 파일명을 가져온다.

        if(!firstFileName.isBlank()) {  // 0번째 인덱스의 파일명이 비어있지 않다면
            log.info("파일 입출력을 합니다.");   // 출력값

            newsFileList = new ArrayList<NewsFile>();

            for(MultipartFile file : newsWriteReqDto.getFiles()) {  // 멀티파트(자료형) 파일(name)에 Dto.fileList에 저장된 값을 모두 가져온다.
                String originFileName = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString(); // 랜덤한 고유 name을 생성한다(문자열로).
                String extension = originFileName.substring(originFileName.lastIndexOf(".")); // 파일명의 마지막인덱스에서 .
                String tempFileName = uuid + extension;
                Path uploadPath = Paths.get(filePath, "news/" + tempFileName);

                File f = new File(filePath + "news");
                if(!f.exists()) {   // 경로에 파일이 존재하지 않으면
                    f.mkdirs(); // 경로에 모든 파일을 생성
                }

                try {
                Files.write(uploadPath, file.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                NewsFile newsFile = NewsFile.builder()
                        .file_origin_name(originFileName)
                        .file_temp_name(tempFileName)
                        .build();

                newsFileList.add(newsFile);
            }
        }

        News news = newsWriteReqDto.toEntity("김준일");
        int result = newsRepository.save(news);


        if(result == 0) {
            return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "새글 작성 실패", news));
        }

        if(newsFileList != null) {
            for(NewsFile newsFile : newsFileList) {
                newsFile.setNews_id(news.getNews_id());
                log.info("NewsFile 객체: {}", newsFile);
            }
            result = newsRepository.saveFiles(newsFileList);

            if(result != newsFileList.size()) {
                return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "파일 업로드 실패", newsFileList));
            }
        }

        NewsWriteRespDto newsWriteRespDto = news.toNewsWriteRespDto(newsFileList);

        return ResponseEntity.ok(new CMRespDto<>(1, "새 글 작성 완료", newsWriteRespDto));
    }

    @GetMapping("/news/{newsId}")
    public ResponseEntity<?> read(@PathVariable int newsId) {

        log.info("{}", newsId);

        News news = newsRepository.getNews(newsId);

        NewsReadRespDto newsReadRespDto = news.toNewsReadRespDto();


        return ResponseEntity.ok(new CMRespDto<>(1, "게시글 불러오기 성공", newsReadRespDto));
    }
}
