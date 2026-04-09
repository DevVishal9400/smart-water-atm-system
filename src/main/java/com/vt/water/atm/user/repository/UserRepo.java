package com.vt.water.atm.user.repository;

import com.vt.water.atm.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    public Optional<User> findByMobile(String mobile);
}
