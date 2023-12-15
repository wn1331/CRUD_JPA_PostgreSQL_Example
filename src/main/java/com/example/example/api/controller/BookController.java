package com.example.example.api.controller;

import com.example.example.application.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

}
