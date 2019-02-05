package se.kth.iv1201.recruitment.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.iv1201.recruitment.domain.AccountDTO;
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

    public void createAccount(AccountDTO accountDTO) throws Exception {
        Person person = new Person(accountDTO.getName(), accountDTO.getSurname(), accountDTO.getEmail(), accountDTO.getSsn(), accountDTO.getPassword(), accountDTO.getUsername());
        Role role = roleRepository.findByName("applicant");
        if (role == null) throw new Exception();
        person.setRole(role);
        personRepository.save(person);
    }
}
