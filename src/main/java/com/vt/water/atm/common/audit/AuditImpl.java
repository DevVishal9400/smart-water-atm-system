package com.vt.water.atm.common.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class AuditImpl implements AuditorAware {
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("ADMIN");
    }
}
