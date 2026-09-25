package com.vt.water.atm.idimpotency.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Idimpotent {
    @Id
    private String idImpotentKey;
    @Lob
    private String response;
    private LocalDateTime createdAt= LocalDateTime.now();

}
