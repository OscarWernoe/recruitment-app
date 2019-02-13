package se.kth.iv1201.recruitment.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Entity representing a role for a person in the database.
 */
@Data
@NoArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long id;
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<Person> personList;

}
