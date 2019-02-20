package se.kth.iv1201.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.recruitment.domain.Role;

import java.util.Optional;

/**
 * Contains all database access concerning roles.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
