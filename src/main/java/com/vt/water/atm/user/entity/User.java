package com.vt.water.atm.user.entity;

import com.vt.water.atm.card.entity.Card;
import com.vt.water.atm.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String password;
    @Column(unique = true)
    private String mobile;
    private String role = "ROLE_USER";
    private String activeStatus = "ACTIVE";
    @OneToOne(cascade = CascadeType.ALL)
    private Card card;


}
