package com.boot.mybatis20220923junho.dto;

import com.boot.mybatis20220923junho.domain.News;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class NewsReadRespDto {
    private int id;
    private String title;
    private String writer;
    private String broadcasting;
    private String content;

    private String createDate;
    private String updateDate;
}
