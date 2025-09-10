package com.example.crud.service;

import com.example.crud.dto.request.BookRequest;
import com.example.crud.dto.response.BookResponse;
import com.example.crud.entity.Book;
import com.example.crud.entity.Category;
import com.example.crud.repository.BookRepository;
import com.example.crud.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public void saveBook(BookRequest bookRequest) {
        Optional<Category> categoryOpt = categoryRepository.findById(bookRequest.categoryId());

        if (categoryOpt.isPresent()) {
            Book newBook = new Book();
            newBook.setName(bookRequest.name());
            newBook.setCategory(categoryOpt.get());

            bookRepository.save(newBook);
        }
    }

    public List<BookResponse> search(String keyword) {
        List<Book> books = bookRepository.searchByName(keyword);
        return books.stream()
                .map(book -> new BookResponse(
                        book.getName(),
                        book.getCategory() != null ? book.getCategory().getName() : "N/A"
                ))
                .collect(Collectors.toList());
    }
}
