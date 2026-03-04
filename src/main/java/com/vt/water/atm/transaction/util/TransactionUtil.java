package com.vt.water.atm.transaction.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TransactionUtil {

    public String generateTransactionId() {

        return UUID.randomUUID().toString().replace("-", "").substring(0, 16);

    }
}
