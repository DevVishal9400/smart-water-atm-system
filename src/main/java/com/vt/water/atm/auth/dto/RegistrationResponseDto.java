package com.vt.water.atm.auth.dto;

import com.vt.water.atm.card.entity.Card;
import com.vt.water.atm.card.entity.dto.CardResponseDto;
import com.vt.water.atm.user.entity.dto.UserResponseDto;
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
