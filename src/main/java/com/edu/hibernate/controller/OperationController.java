package com.edu.hibernate.controller;


import com.edu.hibernate.dto.DebitDto;
import com.edu.hibernate.dto.TopupDto;
import com.edu.hibernate.dto.TransferDto;
import com.edu.hibernate.dto.UserAccountAmountDto;
import com.edu.hibernate.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/operations")
@RestController
public class OperationController {

    private final TransactionService transactionService;

    @PostMapping("/topup")
    public void topup(@RequestBody TopupDto topupDto) {
        transactionService.replenishFunds(topupDto.getToUserAccount(), topupDto.getAmount());
    }


    @PostMapping("/debit")
    public void debit(@RequestBody DebitDto debitDto){
        transactionService.debitFunds(debitDto.getUserAccount(), debitDto.getDebitAmount());
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferDto transferDto){
        transactionService.transferFunds(transferDto);
    }

    @GetMapping("/account_amount")
    public UserAccountAmountDto showUserAccountAmount(@RequestParam String userAccount){
        return transactionService.retrieveUserAccountAmount(userAccount);
    }
}
