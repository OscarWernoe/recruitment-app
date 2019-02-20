package se.kth.iv1201.recruitment.payload;

import lombok.Data;

@Data
public class LoginRequest {
    private final String username;
    private final String password;
}
