package com.vt.water.atm.transaction.mapper;

import com.vt.water.atm.transaction.dto.InititiateTransactionResponseDto;
import com.vt.water.atm.transaction.entity.Transaction;
import lombok.Data;

public class ToInitiateTranRespDto {

    public static InititiateTransactionResponseDto MapToInitiateTranRespDto(Transaction transaction) {
        if (transaction != null) {
            InititiateTransactionResponseDto dto = new InititiateTransactionResponseDto();
            dto.setAmount(transaction.getAmount());
            dto.setStatus(transaction.getStatus());
            dto.setTransactionId(transaction.getTransactionId());
            return dto;
        } else return null;


    }
}
