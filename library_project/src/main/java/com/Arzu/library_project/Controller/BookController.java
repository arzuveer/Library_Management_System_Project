package com.Arzu.library_project.Controller;


import com.Arzu.library_project.DTO.BookRequestDto;
import com.Arzu.library_project.DTO.BookResponseDto;
import com.Arzu.library_project.DTO.BookUpdatePriceDto;
import com.Arzu.library_project.Entity.Book;
import com.Arzu.library_project.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity getBooks()
    {
        List<Book> books;
        try{
            books= bookService.getBooks();
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
       List<BookResponseDto> bookResponseDtos=new ArrayList<>();
       for(Book book: books)
       {
           BookResponseDto bookResponseDto= new BookResponseDto();
           bookResponseDto.setTitle(book.getTitle());
           bookResponseDto.setPrice(book.getPrice());

           bookResponseDtos.add(bookResponseDto);
       }
       return new ResponseEntity(bookResponseDtos, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") int id){
        String response="";
        try{
            response= bookService.deleteById(id);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response,HttpStatus.ACCEPTED);
    }

    @PutMapping("/change_price")
    public ResponseEntity updatePrice(@RequestBody BookUpdatePriceDto bookUpdatePriceDto){
        String response="";
        try{
            response= bookService.updatePrice(bookUpdatePriceDto);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(response,HttpStatus.ACCEPTED);
    }
}
