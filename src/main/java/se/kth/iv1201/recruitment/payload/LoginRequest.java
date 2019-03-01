package se.kth.iv1201.recruitment.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {

    @NotBlank(message = "{username.was.blank}")
    private final String username;

    @NotBlank(message = "{password.was.blank}")
    @Size(min = 8, message = "{password.length.violated}")
    private final String password;
}
