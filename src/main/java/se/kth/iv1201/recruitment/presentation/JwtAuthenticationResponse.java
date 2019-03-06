package se.kth.iv1201.recruitment.presentation;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A class holding the access token as a {@code String} object.
 */
@Data
@NoArgsConstructor
public class JwtAuthenticationResponse {
    private String accessToken;

    /**
     * Creates a new instance with the supplied access token.
     *
     * @param accessToken The access token
     */
    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
