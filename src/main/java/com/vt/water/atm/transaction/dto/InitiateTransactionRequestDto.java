package com.vt.water.atm.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class InitiateTransactionRequestDto {
    @NotBlank(message = "Amount should not be blank!!!")
    private BigDecimal amount;
}
