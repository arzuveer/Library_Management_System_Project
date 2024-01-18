package com.Arzu.library_project.Controller;

import com.Arzu.library_project.DTO.IssueBookRequestDto;
import com.Arzu.library_project.DTO.IssueBookResponseDto;
import com.Arzu.library_project.DTO.ReturnBookRequestDto;
import com.Arzu.library_project.Entity.Transaction;
import com.Arzu.library_project.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.Arzu.library_project.DTO.ReturnBookResponseDto;
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
    @GetMapping("/get_tnxs")
    public String getAllTnxs(@RequestParam("cardNo") int cardNo){

        return transactionService.getAllTnxs(cardNo);

    }
    @GetMapping("/get_ftnxs")
    public String getAllFTnxs(@RequestParam("cardNo") int cardNo){

        return transactionService.getAllFTnxs(cardNo);

    }
    @PostMapping("/return_book")
    public ResponseEntity returnBook(@RequestBody ReturnBookRequestDto returnBookRequestDto) {
        ReturnBookResponseDto returnBookResponseDto;
        try{
            returnBookResponseDto= transactionService.returnBook(returnBookRequestDto);
        }
        catch(Exception e) {
            return  new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(returnBookResponseDto, HttpStatus.ACCEPTED);
    }

}
