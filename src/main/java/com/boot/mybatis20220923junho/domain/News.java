package com.boot.mybatis20220923junho.domain;

import com.boot.mybatis20220923junho.dto.NewsReadRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class News {
    private int news_id;

    private String news_title;
    private String news_writer;
    private String news_broadcasting;
    private String news_content;

    private LocalDateTime create_date;
    private LocalDateTime update_date;

    public NewsReadRespDto toDto() {
        return NewsReadRespDto.builder()
                .id(news_id)
                .title(news_title)
                .writer(news_writer)
                .broadcasting(news_broadcasting)
                .content(news_content)
                .createDate(create_date.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초")))
                .updateDate(update_date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }
}
