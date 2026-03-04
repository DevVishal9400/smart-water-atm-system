package com.vt.water.atm.transaction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class InititiateTransactionResponseDto {

    private String transactionId;
    private BigDecimal amount;
    private String status;
}
