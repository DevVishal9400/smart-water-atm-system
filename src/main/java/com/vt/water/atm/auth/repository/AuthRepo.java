package com.vt.water.atm.auth.repository;

import com.vt.water.atm.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepo extends JpaRepository<User,Integer> {
}
