package com.boot.mybatis20220923junho.repository;

import com.boot.mybatis20220923junho.domain.News;
import com.boot.mybatis20220923junho.dto.NewsWriteReqDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsRepository {
    public int save(News news);
}
