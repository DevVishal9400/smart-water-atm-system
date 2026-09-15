package com.vt.water.atm.transaction.service;

import com.vt.water.atm.auth.service.AuthService;
import com.vt.water.atm.card.entity.Card;
import com.vt.water.atm.card.repositoy.CardRepo;
import com.vt.water.atm.constants.AppConstants;
import com.vt.water.atm.exception.InsufficientBalanceException;
import com.vt.water.atm.exception.TransactionNotFoundException;
import com.vt.water.atm.transaction.dto.ConfirmTransactionResponseDto;
import com.vt.water.atm.transaction.dto.InitiateTransactionRequestDto;
import com.vt.water.atm.transaction.dto.InititiateTransactionResponseDto;
import com.vt.water.atm.transaction.entity.Transaction;
import com.vt.water.atm.transaction.mapper.ToConfirmTransResp;
import com.vt.water.atm.transaction.mapper.ToInitiateTranRespDto;
import com.vt.water.atm.transaction.repository.TransactionRepo;
import com.vt.water.atm.transaction.util.TransactionUtil;
import com.vt.water.atm.user.entity.User;
import com.vt.water.atm.user.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Slf4j
public class TransactionService {
    @Autowired
    private TransactionUtil transactionUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthService authService;
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private CardRepo cardRepo;

    //initiate transaction
    public InititiateTransactionResponseDto initiateTransaction(InitiateTransactionRequestDto initiateTransactionRequestDto) {
        Transaction transaction = this.createTransaction(initiateTransactionRequestDto.getAmount());
        Transaction savedTransaction = this.transactionRepo.save(transaction);
        return ToInitiateTranRespDto.MapToInitiateTranRespDto(savedTransaction);

    }

    //create transaction object
    public Transaction createTransaction(BigDecimal amount) {

        //get logged-in user details

        String loggedInUserMobile = SecurityContextHolder.getContext().getAuthentication().getName();
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

    //confirm transaction
    @Transactional
    public ConfirmTransactionResponseDto confirmTransaction(String transactionId, String mobile, String transactionType) {
        //get the transaction id
        //get card details and update amount
        Transaction transaction = this.transactionRepo.findByTransactionId(transactionId).orElseThrow(() -> new TransactionNotFoundException("Transaction not found for "+transactionId+" transactionId"));

        //check if status is pending
        if (!"PENDING".equalsIgnoreCase(transaction.getStatus()))
            throw new RuntimeException("Transaction already processed!!");

        //fetch user by mobile
        User userByMobileNumber = this.authService.getUserByMobileNumber(mobile);

        //check if transactions belongs to logged to user

        if (!(userByMobileNumber.getCard().getCardNumber().equals(transaction.getCard().getCardNumber())))
            throw new RuntimeException("Unauthorized Access");

        Card cardDetails = transaction.getCard();
        cardDetails =this.setCardBalance(cardDetails,transaction.getAmount(),transactionType);


        transaction.setType(transactionType);
        transaction.setStatus("SUCCESS");

        return ToConfirmTransResp.mapToConfirmTransactionResponseDto(transaction);
    }

    public Card setCardBalance(Card card, BigDecimal amount, String transactionType) {

        if (card == null)
            throw new IllegalArgumentException("card cannot be null");

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("amount must be greater than zero");

        if (transactionType == null || transactionType.isBlank())
            throw new IllegalArgumentException("Transaction type cannot be blank");

        if (AppConstants.Credit.equalsIgnoreCase(transactionType)) {
            card.setBalance(card.getBalance().add(amount));

        } else if (AppConstants.Debit.equalsIgnoreCase(transactionType)) {
            if (card.getBalance().compareTo(amount) >= 0) {
                card.setBalance(card.getBalance().subtract(amount));

            } else {
                throw new InsufficientBalanceException("Insufficient Balance !!!");
            }
        } else {
            throw new IllegalArgumentException("Invalid transaction type!!!");
        }

        return card;
    }
}
