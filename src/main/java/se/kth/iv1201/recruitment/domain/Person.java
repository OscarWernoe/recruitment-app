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

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String ssn;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    /**
     * Creates a new instance with the specified parameters.
     *
     * @param name     The user's name
     * @param surname  The user's surname
     * @param email    The user's email
     * @param ssn      The user's social security number
     * @param username The user's username
     * @param password The user's password
     * @param role     The user's role
     */
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
