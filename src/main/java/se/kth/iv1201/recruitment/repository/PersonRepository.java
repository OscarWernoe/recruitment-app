package se.kth.iv1201.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.recruitment.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
