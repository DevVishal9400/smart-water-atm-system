package com.vt.water.atm.card.repositoy;

import com.vt.water.atm.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepo extends JpaRepository<Card,Long> {
    Optional<Card> findByCardNumber(String cardNumber);
}
