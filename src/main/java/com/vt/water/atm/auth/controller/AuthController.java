package com.vt.water.atm.auth.controller;

import com.vt.water.atm.auth.dto.LoginRequestDto;
import com.vt.water.atm.auth.dto.RegisterRequestDto;
import com.vt.water.atm.auth.dto.RegistrationResponseDto;
import com.vt.water.atm.auth.service.AuthService;
import com.vt.water.atm.common.response.ApiResponse;
import com.vt.water.atm.security.jwt.JWTUtil;
import com.vt.water.atm.security.service.CustomUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

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

    //generate JWT after user validation
    @PostMapping("/jwt")
    public ResponseEntity<ApiResponse> generateJWT(@RequestBody @Valid LoginRequestDto loginRequestDto) {

        //validate using AuthManager, authorization not included
        try {
            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getMobile(), loginRequestDto.getPassword()));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = this.jwtUtil.generateToken(userDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "JWT Generated Successfully", jwtToken, LocalDateTime.now()));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(false, ex.getMessage(), null, LocalDateTime.now()));

        }


    }
}
