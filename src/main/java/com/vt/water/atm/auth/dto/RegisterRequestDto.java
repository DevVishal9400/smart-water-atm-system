package com.vt.water.atm.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;

@Data
@NoArgsConstructor
public class RegisterRequestDto {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Mobile cannot be blank")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number")
    private String mobile;
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
}
