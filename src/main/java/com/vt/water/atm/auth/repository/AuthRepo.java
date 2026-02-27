package com.vt.water.atm.auth.repository;

import com.vt.water.atm.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepo extends JpaRepository<User,Integer> {
    public User findByMobile(String mobile);
    public Optional<User> findByMobileAndPassword(String mobile,String Password);
}
