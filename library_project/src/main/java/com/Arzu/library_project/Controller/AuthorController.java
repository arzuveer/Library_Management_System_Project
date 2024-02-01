package com.Arzu.library_project.Controller;

import com.Arzu.library_project.DTO.AuthorUpdateMobNoRequestDto;
import com.Arzu.library_project.Entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Arzu.library_project.Service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;


    @PostMapping("/add")
    public String addAuthor(@RequestBody Author author)
    {
        authorService.addAuthor(author);
        return "author has been added";
    }
    @GetMapping("/get_authors")
    public List<String> getAuthors()
    {

        return authorService.getAuthors();
    }

    @GetMapping("/get_mobNo/{authorId}")
    public ResponseEntity getMobNoById(@PathVariable("authorId") int authorId){
        String response="";
        try{
            response= authorService.getMobileById(authorId);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/mobNo")
    public ResponseEntity updateMobNo(@RequestBody AuthorUpdateMobNoRequestDto authorUpdateMobNoRequestDto){
        String response;
        try{
            response= authorService.updateMobNo(authorUpdateMobNoRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(response,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/byId")
    public String deleteById(@RequestParam("id") int id){
        return authorService.deleteById(id);
    }

}
