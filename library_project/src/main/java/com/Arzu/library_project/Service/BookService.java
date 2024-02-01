package com.Arzu.library_project.Service;

import com.Arzu.library_project.DTO.BookRequestDto;
import com.Arzu.library_project.DTO.BookResponseDto;
import com.Arzu.library_project.DTO.BookUpdatePriceDto;
import com.Arzu.library_project.DTO.StudentResponseDto;
import com.Arzu.library_project.Entity.Author;
import com.Arzu.library_project.Entity.Book;
import com.Arzu.library_project.Exception.BookNotFoundException;
import com.Arzu.library_project.Repository.AuthorRepository;
import com.Arzu.library_project.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
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

    public List<Book> getBooks() throws BookNotFoundException {
        List<Book> books= new ArrayList<>();
        try{
            books= bookRepository.findAll();
        }
        catch (Exception e){
            throw new BookNotFoundException("no books are there in repository !!");
        }

        return books;
    }

    public String deleteById(int id) throws BookNotFoundException {
        Book book;
        try{
            book = bookRepository.findById(id).get();
        }
        catch (Exception e){
            throw  new BookNotFoundException("invalid book id");
        }
        bookRepository.deleteById(id);
        return  "book deleted !!!";
    }

    public String updatePrice(BookUpdatePriceDto bookUpdatePriceDto) throws BookNotFoundException {
        Book book;
        try{
            book= bookRepository.findById(bookUpdatePriceDto.getBookId()).get();
        }
        catch (Exception e){
            throw new BookNotFoundException("invalid book id");
        }
        book.setPrice(bookUpdatePriceDto.getPrice());
        bookRepository.save(book);
        return "price changed to :"+ book.getPrice();
    }



}
