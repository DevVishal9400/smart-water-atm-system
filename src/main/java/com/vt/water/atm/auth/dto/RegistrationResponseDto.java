package com.vt.water.atm.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponseDto {
    private int userId;
    private String name;
    private String mobile;

}
