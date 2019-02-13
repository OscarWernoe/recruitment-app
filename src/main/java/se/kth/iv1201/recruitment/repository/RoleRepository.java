package se.kth.iv1201.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.recruitment.domain.Role;

/**
 * Contains all database access concerning roles.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
