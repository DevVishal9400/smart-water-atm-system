package com.vt.water.atm.idimpotency.repository;

import com.vt.water.atm.idimpotency.entity.Idimpotent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdImpotancyRepo extends JpaRepository<Idimpotent,String> {
}
