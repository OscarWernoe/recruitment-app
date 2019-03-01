package se.kth.iv1201.recruitment.presentation;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private final String accessToken;
}
