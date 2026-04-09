package com.vt.water.atm.transaction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConfirmTransactionResponseDto {
    private String message;
    private String transactionId;
}
