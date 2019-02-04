package se.kth.iv1201.recruitment.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.iv1201.recruitment.domain.Person;
import se.kth.iv1201.recruitment.domain.Role;
import se.kth.iv1201.recruitment.repository.PersonRepository;
import se.kth.iv1201.recruitment.repository.RoleRepository;

@Service
public class RecruitmentService {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public RecruitmentService(PersonRepository personRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }

    public void addPerson(Person person) {
        Role role = roleRepository.findByName("applicant");
        person.setRole(role);
        personRepository.save(person);
    }
}
