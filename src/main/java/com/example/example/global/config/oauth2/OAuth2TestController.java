package com.example.example.global.config.oauth2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
public class OAuth2TestController {

    @GetMapping("/main")
    public String mainPage() {
        return "http://localhost:8085/oauth2/authorization/google";
    }

    @GetMapping("/mypage")
    public String myPage() {
        return "마이페이지";
    }

}
