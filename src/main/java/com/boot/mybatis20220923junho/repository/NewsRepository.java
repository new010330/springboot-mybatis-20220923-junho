package com.boot.mybatis20220923junho.repository;

import com.boot.mybatis20220923junho.domain.News;
import com.boot.mybatis20220923junho.domain.NewsFile;
import com.boot.mybatis20220923junho.dto.NewsWriteReqDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsRepository {
    public int save(News news);
    public int saveFiles(List<NewsFile> newsFileList);
    public News getNews(int news_id);
}
