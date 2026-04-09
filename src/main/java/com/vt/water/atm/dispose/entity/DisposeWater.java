package com.vt.water.atm.dispose.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class DisposeWater {
    private String cardNumber;
    private BigDecimal amount;

}
