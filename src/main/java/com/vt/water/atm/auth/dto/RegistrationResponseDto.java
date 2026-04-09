package com.vt.water.atm.auth.dto;

import com.vt.water.atm.card.dto.CardResponseDto;
import com.vt.water.atm.user.dto.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponseDto {
    private UserResponseDto userDetails;
    private CardResponseDto cardDetails;

}
