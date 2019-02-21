package se.kth.iv1201.recruitment.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank
    private final String username;

    @NotBlank
    private final String password;
}
