package com.pank.pank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/health")
    public String hello() {
        return "Hello Pankaj, Spring Boot Backend is Live ✅";
    }
}
