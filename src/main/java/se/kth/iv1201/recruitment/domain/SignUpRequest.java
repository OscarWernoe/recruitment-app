package se.kth.iv1201.recruitment.domain;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Email
    @NotBlank
    private String email;

    @Pattern(regexp = "^[0-9]*$")
    @NotBlank
    private String ssn;

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 8)
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
