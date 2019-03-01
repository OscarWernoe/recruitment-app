package se.kth.iv1201.recruitment.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {

    @NotBlank(message = "{name.was.blank}")
    private String name;

    @NotBlank(message = "{surname.was.blank}")
    private String surname;

    @Email(message = "{provide.a.valid.email}")
    @NotBlank(message = "{email.was.blank}")
    private String email;

    @Pattern(regexp = "^[0-9]*$", message = "{ssn.contained.non.numeric.values}")
    @NotBlank(message = "{ssn.was.blank}")
    private String ssn;

    @NotBlank(message = "{username.was.blank}")
    private String username;

    @NotBlank(message = "{password.was.blank}")
    @Size(min = 8, message = "{password.length.violated}")
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
