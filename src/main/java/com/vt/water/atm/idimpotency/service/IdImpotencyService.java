package com.vt.water.atm.idimpotency.service;

import com.vt.water.atm.exception.IdImpotneceKeyRequiredException;
import com.vt.water.atm.idimpotency.entity.Idimpotent;
import com.vt.water.atm.idimpotency.repository.IdImpotancyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IdImpotencyService {
    @Autowired
    private IdImpotancyRepo idImpotancyRepo;

    //check key is exist or not
    public Optional<Idimpotent> isPresent(String key) {
        if (key == null || key.isBlank())
            throw new IdImpotneceKeyRequiredException("IdImpotenceKey is required!!!");

        return this.idImpotancyRepo.findById(key);
    }
}
