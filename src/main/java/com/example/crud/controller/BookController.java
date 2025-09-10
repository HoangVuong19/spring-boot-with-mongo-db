package com.example.crud.controller;

import com.example.crud.dto.request.BookRequest;
import com.example.crud.dto.response.BookResponse;
import com.example.crud.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class BookController {
    private final BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestBody BookRequest bookRequest) {
        bookService.saveBook(bookRequest);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookResponse>> searchBooks(@RequestParam("keyword") String keyword) {
        List<BookResponse> books = bookService.search(keyword);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
