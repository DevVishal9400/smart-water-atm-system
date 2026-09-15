package com.vt.water.atm.dispose.service;

import com.vt.water.atm.card.dto.CardResponseDto;
import com.vt.water.atm.card.entity.Card;
import com.vt.water.atm.card.mapper.ToCardResponseDto;
import com.vt.water.atm.card.repositoy.CardRepo;
import com.vt.water.atm.dispose.dto.DisposeWaterResponseDto;
import com.vt.water.atm.dispose.mapper.ToDisposeWaterResponseDto;
import com.vt.water.atm.exception.InsufficientBalanceException;
import com.vt.water.atm.transaction.dto.ConfirmTransactionResponseDto;
import com.vt.water.atm.transaction.dto.InititiateTransactionResponseDto;
import com.vt.water.atm.transaction.entity.Transaction;
import com.vt.water.atm.transaction.mapper.ToInitiateTransactionRequestDto;
import com.vt.water.atm.transaction.service.TransactionService;
import com.vt.water.atm.transaction.util.TransactionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class DisposeWaterService {
    @Autowired
    private CardRepo cardRepo;
    @Autowired
    private TransactionService transactionService;

    //get card from db check avail balance> requested dispose
    @Transactional
    public DisposeWaterResponseDto getCardDetailsAndDispose(String cardNumber, BigDecimal amount,String transctionType) {

        String loggedInUserMobile = SecurityContextHolder.getContext().getAuthentication().getName();
        Card card = this.cardRepo.findByCardNumber(cardNumber).orElseThrow(() -> new RuntimeException("Invalid Card Details!!!"));

        //check balance
        //initiate transaction with PENDING status
        //deduct amount
        //update transaction as SUCCESS


            InititiateTransactionResponseDto inititiateTransactionResponseDto = this.transactionService.initiateTransaction(ToInitiateTransactionRequestDto.mapToInitiateTransactionRequestDto(amount));



            ConfirmTransactionResponseDto confirmTransactionResponseDto = this.transactionService.confirmTransaction(inititiateTransactionResponseDto.getTransactionId(), loggedInUserMobile,transctionType);
           return ToDisposeWaterResponseDto.mapToDisposeWaterResponseDto(cardNumber,card.getBalance());


    }
}
