package com.vt.water.atm.transaction.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class InitiateTransactionRequestDto {
    @NotNull(message = "Amount is required")
    @Digits(
            integer = 10,
            fraction = 0,
            message = "Amount must be a whole number"
    )
    @Positive(message = "Amount must be greater than 0")
    private BigDecimal amount;
}
