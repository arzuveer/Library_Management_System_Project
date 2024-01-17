package com.Arzu.library_project.Service;

import com.Arzu.library_project.DTO.BookRequestDto;
import com.Arzu.library_project.DTO.BookResponseDto;
import com.Arzu.library_project.Entity.Author;
import com.Arzu.library_project.Entity.Book;
import com.Arzu.library_project.Repository.AuthorRepository;
import com.Arzu.library_project.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    public BookResponseDto addBook(BookRequestDto bookRequestDto) throws Exception {
        Author author = authorRepository.findById(bookRequestDto.getAuthorId()).get();

        Book book = new Book();
        book.setTitle(bookRequestDto.getTitle());
        book.setPrice(bookRequestDto.getPrice());
        book.setGenre(bookRequestDto.getGenre());
        book.setIssued(false);
        book.setAuthor(author);

        author.getBooks().add(book);//book added to author list of books

        authorRepository.save(author);

        BookResponseDto bookResponseDto= new BookResponseDto();
        bookResponseDto.setTitle(book.getTitle());
        bookResponseDto.setPrice(book.getPrice());

        return bookResponseDto;

    }

    public List<Book> getBooks()
    {
        return bookRepository.findAll();
    }
}
