package se.kth.iv1201.recruitment.domain;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * DTO containing user input validations for the sign up request.
 */
@Data
public class SignUpRequest {

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank
    @Size(min = 2, max = 50)
    private String surname;

    @Email
    @NotBlank
    @Size(max = 50)
    private String email;

    @Pattern(regexp = "^[0-9]*$")
    @NotBlank
    @Size(min = 10, max = 10)
    private String ssn;

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    @Size(min = 8, max = 100)
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
