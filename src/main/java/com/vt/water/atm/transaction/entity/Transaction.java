package com.vt.water.atm.transaction.entity;

import com.vt.water.atm.card.entity.Card;
import com.vt.water.atm.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
public class Transaction extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private String status;
    @Column(unique = true, nullable = false)
    private String transactionId;
    @ManyToOne(optional = false)
    private Card card;
    private String type;
}
