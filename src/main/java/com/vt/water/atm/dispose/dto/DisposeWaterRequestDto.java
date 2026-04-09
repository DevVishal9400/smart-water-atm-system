package com.vt.water.atm.dispose.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class DisposeWaterRequestDto {
    @NotBlank(message = "Card number should not be empty!!!")
    private String cardNumber;
    @Positive(message = "Amount must be grater than zero!!!")
    private BigDecimal amount;
}
