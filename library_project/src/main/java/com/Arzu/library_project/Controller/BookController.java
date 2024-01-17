package com.Arzu.library_project.Controller;


import com.Arzu.library_project.DTO.BookRequestDto;
import com.Arzu.library_project.DTO.BookResponseDto;
import com.Arzu.library_project.Entity.Book;
import com.Arzu.library_project.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public BookResponseDto addBook(@RequestBody BookRequestDto bookRequestDto) throws Exception {
        return  bookService.addBook(bookRequestDto);
    }

    @GetMapping("/get_books")
    public List<Book> getBooks()
    {
        return bookService.getBooks();
    }
}
