package se.kth.iv1201.recruitment.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {

    @NotBlank
    private final String username;

    @NotBlank
    @Size(min = 8)
    private final String password;
}
