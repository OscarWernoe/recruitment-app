package se.kth.iv1201.recruitment.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AccountDTO {
    private final String name;
    private final String surname;
    private final String email;
    private final String ssn;
    private final String password;
    private final String username;

}
