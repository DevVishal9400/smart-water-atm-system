package com.vt.water.atm.user.mapper;

import com.vt.water.atm.user.entity.User;
import com.vt.water.atm.user.dto.UserResponseDto;

public class ToUserResponseDto {

    public static UserResponseDto mapToUserResponseDto(User user){
        if(user!=null){
            UserResponseDto userResponseDto=new UserResponseDto();
            userResponseDto.setMobile(user.getMobile());
            userResponseDto.setName(user.getName());

            return userResponseDto;
        }else return null;

    }
}
