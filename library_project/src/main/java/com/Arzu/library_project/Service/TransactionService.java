package com.Arzu.library_project.Service;

import com.Arzu.library_project.DTO.IssueBookRequestDto;
import com.Arzu.library_project.DTO.IssueBookResponseDto;
import com.Arzu.library_project.Entity.Book;
import com.Arzu.library_project.Entity.LibraryCard;
import com.Arzu.library_project.Entity.Transaction;
import com.Arzu.library_project.Enum.CardStatus;
import com.Arzu.library_project.Enum.TransactionStatus;
import com.Arzu.library_project.Repository.BookRepository;
import com.Arzu.library_project.Repository.LibraryCardRepository;
import com.Arzu.library_project.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    JavaMailSender emailSender;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    LibraryCardRepository libraryCardRepository;

    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception {
        Transaction transaction= new Transaction();
        transaction.setTransactionId(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(true);


        LibraryCard card;
        try{
            card= libraryCardRepository.findById(issueBookRequestDto.getCardId()).get();
        }
        catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid card id");
            transactionRepository.save(transaction);
            throw new Exception("Invalid card id");
        }
        Book book;
        try{
            book= bookRepository.findById(issueBookRequestDto.getBookId()).get();
        }
        catch(Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid book id");
            transactionRepository.save(transaction);
            throw new Exception("Invalid book id");
        }

        transaction.setBook(book);
        transaction.setLibraryCard(card);

        // now if both id is valid then
        if(card.getStatus() != CardStatus.ACTIVATED) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("card not activated");
            transactionRepository.save(transaction);
            throw new Exception("card not activated");
        }
        if(book.isIssued()==true) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("sorry! book already issued");
            transactionRepository.save(transaction);
            throw new Exception("sorry! book already issued");
        }
        // now we can issue book
        book.setIssued(true);
        book.setCard(card);
        book.getTransaction().add(transaction);
        card.getTransactionList().add(transaction);
        card.getBookIssued().add(book);

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setMessage("transaction was successful");
        libraryCardRepository.save(card);// this will save all 3 objects

        // prepare dto
        IssueBookResponseDto issueBookResponseDto= new IssueBookResponseDto();
        issueBookResponseDto.setBookName(book.getTitle());
        issueBookResponseDto.setTransactionId(transaction.getTransactionId());
        issueBookResponseDto.setTransactionStatus(TransactionStatus.SUCCESS);


        String text= "congrats !! "+card.getStudent().getName()+" you have been issued :"+ book.getTitle()+" book." +
                " and your transaction id is "+ transaction.getTransactionId()+" Thank you for using our services";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("arzubackend443@gmail.com");
        message.setTo(card.getStudent().getEmail());
        message.setSubject("issue book notification");
        message.setText(text);
        emailSender.send(message);


        return issueBookResponseDto;
    }
}
