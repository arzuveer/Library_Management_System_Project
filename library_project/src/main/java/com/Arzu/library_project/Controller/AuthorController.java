package com.Arzu.library_project.Controller;

import com.Arzu.library_project.Entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
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

}
