package com.vt.water.atm.auth.mapper;

import com.vt.water.atm.auth.dto.RegisterRequestDto;
import com.vt.water.atm.user.entity.User;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public static User toUser(@Nullable  RegisterRequestDto registerRequestDto){

        if(registerRequestDto==null)
            return null;

        User user=new User();
        user.setName(registerRequestDto.getName());
        user.setMobile(registerRequestDto.getMobile());
        user.setPassword(registerRequestDto.getPassword());
        return user;
    }

}
