package com.vt.water.atm.dispose.mapper;

import com.vt.water.atm.card.dto.CardResponseDto;
import com.vt.water.atm.dispose.dto.DisposeWaterResponseDto;

import java.math.BigDecimal;

public class ToDisposeWaterResponseDto {

    public static DisposeWaterResponseDto mapToDisposeWaterResponseDto(String cardNumber, BigDecimal balance) {
        if (cardNumber != null && !cardNumber.isBlank() && balance != null) {
            CardResponseDto cardResponseDto = new CardResponseDto();
            cardResponseDto.setCardNumber(cardNumber);
            cardResponseDto.setBalance(balance);
            DisposeWaterResponseDto disposeWaterResponseDto = new DisposeWaterResponseDto();
            disposeWaterResponseDto.setCardResponseDto(cardResponseDto);
            disposeWaterResponseDto.setStatus("SUCCESS");
            return disposeWaterResponseDto;
        } else return null;
    }

}

