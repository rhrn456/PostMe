package com.spring.postme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/main")
    public String showMainPage() {
        return "main"; // 'views' 폴더 내의 'main.jsp'를 가리킵니다.
    }
}
