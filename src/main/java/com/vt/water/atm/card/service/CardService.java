package com.vt.water.atm.card.service;

import com.vt.water.atm.card.dto.CardResponseDto;
import com.vt.water.atm.card.entity.Card;
import com.vt.water.atm.card.mapper.ToCardResponseDto;
import com.vt.water.atm.card.repositoy.CardRepo;
import com.vt.water.atm.exception.CardNotFoundException;
import org.springframework.stereotype.Service;

import javax.smartcardio.CardNotPresentException;
import java.util.Optional;
@Service
public class CardService {

    private CardRepo cardRepo;

    //get single card details
    public CardResponseDto getCardDetails(String cardNumber) {
        Optional<Card> cardFromDb = this.cardRepo.findByCardNumber(cardNumber);
        return ToCardResponseDto.mapToCardResponseDto(cardFromDb.orElseThrow(() -> new CardNotFoundException("Card not found for cardNumber :" + cardNumber)));
    }
}
