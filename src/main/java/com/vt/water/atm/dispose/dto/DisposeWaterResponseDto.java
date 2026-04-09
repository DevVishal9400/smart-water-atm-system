package com.vt.water.atm.dispose.dto;

import com.vt.water.atm.card.dto.CardResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisposeWaterResponseDto {
    private CardResponseDto cardResponseDto;
    private String status;
}
