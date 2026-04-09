package com.vt.water.atm.transaction.mapper;

import com.vt.water.atm.transaction.dto.ConfirmTransactionResponseDto;
import com.vt.water.atm.transaction.entity.Transaction;

public class ToConfirmTransResp {

    public static ConfirmTransactionResponseDto mapToConfirmTransactionResponseDto(Transaction transaction) {
        if (transaction != null) {
            ConfirmTransactionResponseDto dto = new ConfirmTransactionResponseDto();
            dto.setTransactionId(transaction.getTransactionId());
            dto.setMessage("Transaction success..");
            return dto;
        }
        return null;
    }
}
