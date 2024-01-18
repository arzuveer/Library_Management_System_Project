package com.Arzu.library_project.DTO;

import com.Arzu.library_project.Enum.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReturnBookResponseDto {
    private String transactionId;

    private String bookName;

    private String response;

   private TransactionStatus transactionStatus;
}
