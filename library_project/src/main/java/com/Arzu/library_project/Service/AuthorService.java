package com.Arzu.library_project.Service;

import com.Arzu.library_project.DTO.AuthorUpdateMobNoRequestDto;
import com.Arzu.library_project.Entity.Author;
import com.Arzu.library_project.Exception.AuthorNotFoundException;
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
    public String getMobileById(int authorId) throws AuthorNotFoundException {
       Author author;
       try{
           author= authorRepository.findById(authorId).get();
       }
       catch (Exception e){
           throw new AuthorNotFoundException("Invalid author id");
       }
       // author exits so now get mobile no
       return author.getMobNo();
    }

    public String updateMobNo(AuthorUpdateMobNoRequestDto authorUpdateMobNoRequestDto) throws AuthorNotFoundException {
        Author author;
        try{
            author = authorRepository.findById(authorUpdateMobNoRequestDto.getAuthorId()).get();
        }
        catch (Exception e){
            throw new AuthorNotFoundException("invalid Author id");
        }
        // author exists now update mobno
        author.setMobNo(authorUpdateMobNoRequestDto.getNewMobNo());
        authorRepository.save(author);

        return "Number changed to: "+ author.getMobNo() ;
    }

    public String deleteById(int id){
        authorRepository.deleteById(id);
        return  "author deleted successfully";
    }
}
