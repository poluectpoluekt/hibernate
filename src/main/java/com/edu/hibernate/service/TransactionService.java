package com.edu.hibernate.service;

import com.edu.hibernate.dao.TransactionDao;
import com.edu.hibernate.dto.TransferDto;
import com.edu.hibernate.dto.UserAccountAmountDto;
import com.edu.hibernate.entity.User;
import com.edu.hibernate.entity.operations.Debit;
import com.edu.hibernate.entity.operations.Topup;
import com.edu.hibernate.entity.operations.Transfer;
import com.edu.hibernate.exception.transaction.NegativeUserBalanceException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class TransactionService {

    private final TransactionDao repository;
    private final UserService userService;

    @Transactional
    public void transferFunds(TransferDto transferDto) {
        User userFrom = userService.findUserByAccountNumber(transferDto.getFromUserAccount());
        User userTo = userService.findUserByAccountNumber(transferDto.getToUserAccount());

        if (userFrom.getBalance().subtract(transferDto.getAmount()).compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeUserBalanceException();
        }

        userFrom.setBalance(userFrom.getBalance().subtract(transferDto.getAmount()));
        userTo.setBalance(userTo.getBalance().add(transferDto.getAmount()));

        Transfer transfer = new Transfer();
        transfer.setFrom(transferDto.getFromUserAccount());
        transfer.setTarget(transferDto.getToUserAccount());
        transfer.setAmount(transferDto.getAmount());

        userService.updateUser(userFrom);
        userService.updateUser(userTo);
        repository.create(transfer);
    }
    @Transactional
    public void replenishFunds(String userAccount, BigDecimal amount) {
        User user = userService.findUserByAccountNumber(userAccount);
        user.setBalance(user.getBalance().add(amount));

        Topup topup = new Topup();
        topup.setUserAccountNumber(userAccount);
        topup.setAmount(amount);
        topup.setOwner(user);

        userService.updateUser(user);
        repository.create(topup);
    }

    @Transactional
    public void debitFunds(String userAccount, BigDecimal amount) {
        User user = userService.findUserByAccountNumber(userAccount);
        if (user.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeUserBalanceException();
        }
        user.setBalance(user.getBalance().subtract(amount));

        Debit debit = new Debit();
        debit.setUserAccountNumber(userAccount);
        debit.setAmount(amount);
        debit.setOwner(user);

        userService.updateUser(user);
        repository.create(debit);
    }

    @Transactional(readOnly = true)
    public UserAccountAmountDto retrieveUserAccountAmount(String userAccount) {

        User user = userService.findUserByAccountNumber(userAccount);
        return new UserAccountAmountDto(user.getUsername(), user.getBalance());
    }
}
