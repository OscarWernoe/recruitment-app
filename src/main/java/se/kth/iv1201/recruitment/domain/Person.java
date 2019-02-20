package se.kth.iv1201.recruitment.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity representing a person in the database.
 */
@Entity
@Data
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private long id;

    private String name;

    private String surname;

    private String email;

    private String ssn;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public Person(String name, String surname, String email, String ssn, String username, String password, Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.ssn = ssn;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
