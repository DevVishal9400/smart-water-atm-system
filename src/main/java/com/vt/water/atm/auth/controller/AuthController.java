package com.vt.water.atm.auth.controller;

import com.vt.water.atm.auth.dto.RegisterRequestDto;
import com.vt.water.atm.auth.dto.RegistrationResponseDto;
import com.vt.water.atm.auth.service.AuthService;
import com.vt.water.atm.common.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    //register the user
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegistrationResponseDto>> registerUser(@RequestBody @Valid RegisterRequestDto registerRequestDto) {

        RegistrationResponseDto registrationResponseDto = this.authService.registerUser(registerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<RegistrationResponseDto>(
                true,
                "User registered successfully",
                registrationResponseDto,
                LocalDateTime.now()
                ));
    }
}
