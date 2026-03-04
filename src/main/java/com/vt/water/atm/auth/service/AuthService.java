package com.vt.water.atm.auth.service;

import com.vt.water.atm.auth.dto.RegisterRequestDto;
import com.vt.water.atm.auth.dto.RegistrationResponseDto;
import com.vt.water.atm.auth.mapper.AuthMapper;
import com.vt.water.atm.auth.repository.AuthRepo;
import com.vt.water.atm.card.entity.Card;
import com.vt.water.atm.card.entity.dto.CardResponseDto;
import com.vt.water.atm.card.entity.mapper.ToCardResponseDto;
import com.vt.water.atm.card.entity.util.CardUtil;
import com.vt.water.atm.exception.UserAlreadyExistsException;
import com.vt.water.atm.user.entity.User;
import com.vt.water.atm.user.entity.dto.UserResponseDto;
import com.vt.water.atm.user.entity.mapper.ToUserResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AuthService {

    @Autowired
    private AuthRepo authRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CardUtil cardUtil;

    //register the user and create card with o Rs balance initially.
    @Transactional
    public RegistrationResponseDto registerUser(RegisterRequestDto registerRequestDto) {

        //check user with same mobile already exists or not
        User existingMobileuser = this.authRepo.findByMobile(registerRequestDto.getMobile());
        if (existingMobileuser != null)
            throw new UserAlreadyExistsException("User Already exists with mobile number!!!");

        User user = AuthMapper.toUser(registerRequestDto);
        //encode password before save
        user.setPassword(this.passwordEncoder.encode(registerRequestDto.getPassword()));

        //craete card for user
        String randonCardNumber = this.cardUtil.generateCardNumber();
        Card newcard = new Card();
        newcard.setCardNumber(randonCardNumber);
        newcard.setBalance(BigDecimal.ZERO);

        //set card for user
        user.setCard(newcard);

        User savedUser = this.authRepo.save(user);

        UserResponseDto userResponseDto = ToUserResponseDto.mapToUserResponseDto(savedUser);
        CardResponseDto cardResponseDto = ToCardResponseDto.mapToCardResponseDto(newcard);
        return new RegistrationResponseDto(userResponseDto, cardResponseDto);

    }

    //get user by mobile
    public User getUserByMobileNumber(String mobile) {

        if(mobile!=null && !mobile.isBlank()){
            User userByMobile = this.authRepo.findByMobile(mobile);
            if (userByMobile == null)
                throw new UsernameNotFoundException("User With Given Mobile Not Exists!!!!");
            else
                return userByMobile;
        }else
            throw new IllegalArgumentException("Invalid arguments!!!");

    }
}
