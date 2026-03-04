package com.vt.water.atm.transaction.service;

import com.vt.water.atm.auth.service.AuthService;
import com.vt.water.atm.transaction.dto.InitiateTransactionRequestDto;
import com.vt.water.atm.transaction.dto.InititiateTransactionResponseDto;
import com.vt.water.atm.transaction.entity.Transaction;
import com.vt.water.atm.transaction.mapper.ToInitiateTranRespDto;
import com.vt.water.atm.transaction.repository.TransactionRepo;
import com.vt.water.atm.transaction.util.TransactionUtil;
import com.vt.water.atm.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {
    @Autowired
    private TransactionUtil transactionUtil;
    @Autowired
    private Authentication authentication;
    @Autowired
    private AuthService authService;
    @Autowired
    private TransactionRepo transactionRepo;

    //initiate transaction
    public InititiateTransactionResponseDto initiateTransaction(InitiateTransactionRequestDto initiateTransactionRequestDto) {
        Transaction transaction = this.createTransaction(initiateTransactionRequestDto.getAmount());
        Transaction savedTransaction = this.transactionRepo.save(transaction);
        return ToInitiateTranRespDto.MapToInitiateTranRespDto(savedTransaction);


    }

    //create transaction object
    public Transaction createTransaction(BigDecimal amount) {

        //get logged-in user details
        String loggedInUserMobile = this.authentication.getName();
//fetch user by mobile
        User userByMobileNumber = this.authService.getUserByMobileNumber(loggedInUserMobile);

        //create Transaction object
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionId(this.transactionUtil.generateTransactionId());
        transaction.setStatus("PENDING");
        transaction.setCard(userByMobileNumber.getCard());

        return transaction;

    }
}
