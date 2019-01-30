package se.kth.iv1201.recruitment.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long roleId;

    @NotNull
    private String name;
}
