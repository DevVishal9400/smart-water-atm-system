package com.vt.water.atm.dispose.service;

import com.vt.water.atm.card.dto.CardResponseDto;
import com.vt.water.atm.card.entity.Card;
import com.vt.water.atm.card.mapper.ToCardResponseDto;
import com.vt.water.atm.card.repositoy.CardRepo;
import com.vt.water.atm.dispose.dto.DisposeWaterResponseDto;
import com.vt.water.atm.transaction.entity.Transaction;
import com.vt.water.atm.transaction.util.TransactionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class DisposeWaterService {
    @Autowired
    private CardRepo cardRepo;

    //get card from db check avail balance> requested dispose
    @Transactional
    public DisposeWaterResponseDto getCardDetailsAndDispose(String cardNumber, BigDecimal amount) {

        Card card = this.cardRepo.findByCardNumber(cardNumber).orElseThrow(() -> new RuntimeException("Invalid Card Details!!!"));

        if (card.getBalance().compareTo(amount) >= 0) {
            card.setBalance(card.getBalance().subtract(amount));

            Transaction transaction = new Transaction();
            transaction.setCard(card);
            transaction.setTransactionId(new TransactionUtil().generateTransactionId());
            transaction.setAmount(amount);
            transaction.setStatus("SUCCESS");
            transaction.setType("DEBIT");

        } else {
            throw new RuntimeException("Insufficient Balance!!!");
        }
        System.out.println("just tp");
        return new DisposeWaterResponseDto(ToCardResponseDto.mapToCardResponseDto(card), "SUCCESS");
    }
}
