package com.boot.mybatis20220923junho.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CMRespDto<T> {
    private int code;
    private String msg;
    private T data;
}
