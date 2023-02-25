package com.pme.dto;

import com.pme.utils.Validate;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Getter
public class NewMemberRequestDTO extends UpdateMemberRequestDTO {
    @Pattern(message = Validate.validPassword, regexp = Validate.PASSWORD_REGEX)
    private String password;
    private String confirmPassword;
}
