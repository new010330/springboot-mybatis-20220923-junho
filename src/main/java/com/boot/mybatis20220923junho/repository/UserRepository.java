package com.boot.mybatis20220923junho.repository;

import com.boot.mybatis20220923junho.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    public int save(User user);
}
