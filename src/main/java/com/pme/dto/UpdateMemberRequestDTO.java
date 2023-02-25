package com.pme.dto;

import com.pme.utils.Validate;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;

@NoArgsConstructor @AllArgsConstructor @Getter
public class UpdateMemberRequestDTO {
    @Size(min = 3,  message = Validate.minLength + "3")
    @Pattern(regexp="^[A-Za-z]*$", message = Validate.validAlphabets)
    @NotBlank(message = Validate.validNotBlank)
    private String firstName;

    @Size(min = 3,  message = Validate.minLength + "3")
    @Pattern(regexp="^[A-Za-z]*$", message = Validate.validAlphabets)
    @NotBlank(message = Validate.validNotBlank)
    private String lastName;

    @Email(regexp = Validate.EMAIL_REGEX, message = Validate.validEmail)
    private String email;

    @Digits(message = Validate.validPhoneNo, fraction = 0, integer = 3)
    private Integer countryCode;

    @Digits(message = Validate.validPhoneNo, fraction = 0, integer = 15)
    private Long phoneNo;

    @Size(min = 15,  message = "Enter a valid address")
    @NotBlank(message = Validate.validNotBlank)
    private String address;

    @NotBlank(message = Validate.validNotBlank)
    private String track;

    private Collection<String> stack;
}
