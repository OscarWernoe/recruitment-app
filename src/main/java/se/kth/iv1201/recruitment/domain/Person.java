package se.kth.iv1201.recruitment.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private long personId;

    @Column(name = "first_name")
    private final String firstName;

    @Column(name = "last_name")
    private final String lastName;

    private final String email;

    private final String ssn;

    private final String password;

    @Column(unique = true)
    private final String username;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
