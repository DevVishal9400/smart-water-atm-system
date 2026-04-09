package com.vt.water.atm.transaction.controller;

import com.vt.water.atm.common.response.ApiResponse;
import com.vt.water.atm.transaction.dto.ConfirmTransactionResponseDto;
import com.vt.water.atm.transaction.dto.InitiateTransactionRequestDto;
import com.vt.water.atm.transaction.dto.InititiateTransactionResponseDto;
import com.vt.water.atm.transaction.mapper.ToConfirmTransResp;
import com.vt.water.atm.transaction.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/transaction")
@Validated
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AuthenticationManager manager;

    @PostMapping("/initiate")
    public ResponseEntity<ApiResponse> initiateTransaction(@RequestBody @Valid InitiateTransactionRequestDto initiateTransactionRequestDto) {
        InititiateTransactionResponseDto inititiateTransactionResponseDto = this.transactionService.initiateTransaction(initiateTransactionRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<InititiateTransactionResponseDto>(
                true,
                "Transaction initiated successfully",
                inititiateTransactionResponseDto,
                LocalDateTime.now()
        ));

    }

    //confirm transaction - mean payment received
    @PostMapping("/{transactionId}/confirm")
    public ResponseEntity<ApiResponse> confirmTransaction(@PathVariable("transactionId") @NotBlank(message = "Transaction id is required!!!") String transactionId) {

        //get mobile from JWT
        String mobile = SecurityContextHolder.getContext().getAuthentication().getName();

        ConfirmTransactionResponseDto confirmTransactionResponseDto = this.transactionService.confirmTransaction(transactionId, mobile);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<ConfirmTransactionResponseDto>(
                true,
                "Transaction Confirm success",
                confirmTransactionResponseDto,
                LocalDateTime.now()

        ));

    }

}
