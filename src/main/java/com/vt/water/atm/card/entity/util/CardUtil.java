package com.vt.water.atm.card.entity.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CardUtil {

    public String generateCardNumber() {

        return UUID.randomUUID().toString().replace("-", "").substring(0, 16);

    }
}
