package com.Arzu.library_project.Repository;

import com.Arzu.library_project.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    @Query(value = "select * from transaction t where t.Library_card_card_no=:cardId AND t.transaction_status='SUCCESS'", nativeQuery = true )
    List<Transaction> getAllSucessfullTnxsWithCardNo(int cardId);


    @Query(value = "select * from transaction t where t.Library_card_card_no=:cardId AND t.transaction_status='FAILED'", nativeQuery = true )
    List<Transaction> getAllFailedTnxsWithCardNo(int cardId);
}
