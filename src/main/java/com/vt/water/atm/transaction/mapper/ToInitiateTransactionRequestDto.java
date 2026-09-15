package com.vt.water.atm.transaction.mapper;

import com.vt.water.atm.transaction.dto.InitiateTransactionRequestDto;

import java.math.BigDecimal;

public class ToInitiateTransactionRequestDto {

   public static InitiateTransactionRequestDto mapToInitiateTransactionRequestDto(BigDecimal amount){
        if(amount!=null){
            InitiateTransactionRequestDto initiateTransactionRequestDto=new InitiateTransactionRequestDto();
            initiateTransactionRequestDto.setAmount(amount);
            return initiateTransactionRequestDto;
        }else return null;

    }
}
