package com.vt.water.atm.card.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class CardResponseDto {
    private BigDecimal balance;
    private String cardNumber;

}
