package se.kth.iv1201.recruitment.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * Entity representing a person in the database.
 */
@Data
@RequiredArgsConstructor
@Entity
public class Person {
    private final String name;
    private final String surname;
    private final String email;
    private final String ssn;
    private final String password;

    @Column(unique = true)
    private final String username;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
