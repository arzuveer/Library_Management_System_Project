package com.Arzu.library_project.Controller;

import com.Arzu.library_project.DTO.IssueBookRequestDto;
import com.Arzu.library_project.DTO.IssueBookResponseDto;
import com.Arzu.library_project.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issue_book")
    public ResponseEntity issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto) {
        IssueBookResponseDto issueBookResponseDto;
        try{
            issueBookResponseDto= transactionService.issueBook(issueBookRequestDto);
        }
        catch(Exception e) {
            return  new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(issueBookResponseDto, HttpStatus.ACCEPTED);
    }
}
