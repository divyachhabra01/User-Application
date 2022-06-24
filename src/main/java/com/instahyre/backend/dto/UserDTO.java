package com.instahyre.backend.dto;


import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
@Builder
public class UserDTO {

    @NotNull
    private String userName;
    @NotEmpty(message = "This field is required")
    @Digits(integer = 10, fraction = 0, message = "Please enter a valid Phone Number")
    private String phoneNumber;
    @Pattern(regexp = ".+@.+\\..+", message = "Please enter a valid Email")
    private String email;
    @NotEmpty(message = "This field is required")
    private String password;
    private Boolean isSpam;
}
