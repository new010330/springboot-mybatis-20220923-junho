package com.boot.mybatis20220923junho.dto;

import com.boot.mybatis20220923junho.domain.News;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class NewsWriteReqDto {
    private String title;
    private String broadcastingName;
    private String writer;
    private String content;
    private List<MultipartFile> files;

    public News toEntity() {
        return News.builder()
                .news_title(title)
                .news_writer(writer)
                .news_broadcasting(broadcastingName)
                .news_content(content)
                .build();
    }
}
