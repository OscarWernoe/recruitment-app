package se.kth.iv1201.recruitment.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class SignUpRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String ssn;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public SignUpRequest(String name, String surname, String email, String ssn, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.ssn = ssn;
        this.username = username;
        this.password = password;
    }
}
