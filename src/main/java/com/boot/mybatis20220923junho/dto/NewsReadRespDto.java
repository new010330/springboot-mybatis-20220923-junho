package com.boot.mybatis20220923junho.dto;

import com.boot.mybatis20220923junho.domain.News;
import com.boot.mybatis20220923junho.domain.NewsFile;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class NewsReadRespDto {
    private int id;
    private String title;
    private String writer;
    private String broadcastingName;
    private String content;
    private List<NewsFile> fileList;

    private String createDate;
    private String updateDate;
}
