package com.vt.water.atm.auth.service;

import com.vt.water.atm.auth.dto.RegisterRequestDto;
import com.vt.water.atm.auth.dto.RegistrationResponseDto;
import com.vt.water.atm.auth.mapper.AuthMapper;
import com.vt.water.atm.auth.repository.AuthRepo;
import com.vt.water.atm.exception.UserAlreadyExistsException;
import com.vt.water.atm.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthRepo authRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;


    //register the user
    public RegistrationResponseDto registerUser(RegisterRequestDto registerRequestDto){

        //check user with same mobile already exists or not
        User existingMobileuser = this.authRepo.findByMobile(registerRequestDto.getMobile());
        if(existingMobileuser!=null)
            throw new UserAlreadyExistsException("User Already exists with mobile number!!!");

        User user = AuthMapper.toUser(registerRequestDto);
        //encode password before save
        user.setPassword(this.passwordEncoder.encode(registerRequestDto.getPassword()));
        User savedUser = this.authRepo.save(user);

        return new RegistrationResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getMobile());

    }
}
