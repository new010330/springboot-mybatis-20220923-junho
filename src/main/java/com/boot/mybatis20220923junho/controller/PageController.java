package com.boot.mybatis20220923junho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/news/newpost")
    public String loadWriteNews() {
        return "/news/write";
    }
}
