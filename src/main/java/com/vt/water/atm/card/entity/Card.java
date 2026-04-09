package com.vt.water.atm.card.entity;

import com.vt.water.atm.transaction.entity.Transaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true,nullable = false)
    private String cardNumber;
    private BigDecimal balance;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "card")
    private List<Transaction> transaction;
}
