package com.vt.water.atm.dispose.controller;

import com.vt.water.atm.card.dto.CardResponseDto;
import com.vt.water.atm.card.service.CardService;
import com.vt.water.atm.common.response.ApiResponse;
import com.vt.water.atm.dispose.dto.DisposeWaterRequestDto;
import com.vt.water.atm.dispose.dto.DisposeWaterResponseDto;
import com.vt.water.atm.dispose.service.DisposeWaterService;
import com.vt.water.atm.exception.InsufficientBalanceException;
import com.vt.water.atm.transaction.dto.InitiateTransactionRequestDto;
import com.vt.water.atm.transaction.dto.InititiateTransactionResponseDto;
import com.vt.water.atm.transaction.mapper.ToInitiateTranRespDto;
import com.vt.water.atm.transaction.mapper.ToInitiateTransactionRequestDto;
import com.vt.water.atm.transaction.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/dispose")
public class DisposeWaterController {

    @Autowired
    private CardService cardService;
    @Autowired
    private DisposeWaterService disposeWaterService;

    //dispose water & reduce balance
    @PostMapping
    public ResponseEntity<ApiResponse<DisposeWaterResponseDto>> disposeWater(@RequestBody @Valid DisposeWaterRequestDto disposeWaterRequestDto) {
        DisposeWaterResponseDto cardDetailsAndDispose = this.disposeWaterService.getCardDetailsAndDispose(disposeWaterRequestDto.getCardNumber(), disposeWaterRequestDto.getAmount(),disposeWaterRequestDto.getTransactionType());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<DisposeWaterResponseDto>(true,
                        "Balance Updated, pls procced to Dispose",
                        cardDetailsAndDispose,
                        LocalDateTime.now()
                )
        );
    }
}
