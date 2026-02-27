package com.vt.water.atm.security.service;

import com.vt.water.atm.auth.repository.AuthRepo;
import com.vt.water.atm.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AuthRepo authRepo;

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {

        //get user with mobile
        User user = this.authRepo.findByMobile(mobile);
        if (user == null)
            throw new UsernameNotFoundException("User with given Mobile is not available in System!!!");

        return new CustomUserDetails(user);
    }
}
