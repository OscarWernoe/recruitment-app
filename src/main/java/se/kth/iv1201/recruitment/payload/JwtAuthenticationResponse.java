package se.kth.iv1201.recruitment.payload;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private final String accessToken;
}
