package com.Arzu.library_project.Service;

import com.Arzu.library_project.Entity.Author;
import com.Arzu.library_project.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public void addAuthor(Author author)
    {
        authorRepository.save(author);
    }

    public List<String> getAuthors()
    {
        List<String> authors=new ArrayList<>();
        List<Author> authorList=authorRepository.findAll();
        for(Author author:authorList)
        {
            authors.add(author.getName());
        }
        return authors;
    }
}
