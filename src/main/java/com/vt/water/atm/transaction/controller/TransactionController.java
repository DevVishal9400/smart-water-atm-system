package com.vt.water.atm.transaction.controller;

import com.vt.water.atm.transaction.dto.InitiateTransactionDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @PostMapping("/initiate")
    public ResponseEntity initiateTransaction(@RequestBody @Valid InitiateTransactionDto initiateTransactionDto) {


        return null;

    }
}
