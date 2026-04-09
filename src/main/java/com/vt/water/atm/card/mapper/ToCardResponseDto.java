package com.vt.water.atm.card.mapper;

import com.vt.water.atm.card.entity.Card;
import com.vt.water.atm.card.dto.CardResponseDto;

public class ToCardResponseDto {

    public static CardResponseDto mapToCardResponseDto(Card card) {

        if (card != null) {
            CardResponseDto cardResponseDto = new CardResponseDto();
            cardResponseDto.setCardNumber(card.getCardNumber());
            cardResponseDto.setBalance(card.getBalance());

            return
                    cardResponseDto;
        } else return null;


    }
}
