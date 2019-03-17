package se.kth.iv1201.recruitment.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.recruitment.domain.*;
import se.kth.iv1201.recruitment.repository.PersonRepository;
import se.kth.iv1201.recruitment.repository.RoleRepository;

import javax.validation.Valid;

/**
 * The service that specifies tasks to be performed by the repository layer.
 * The class is annotated using {@code @Transactional}, thus, all the methods will be performed using transactions.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RecruitmentService {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * A constructor for creating a RecruitmentService instance.
     *
     * @param personRepository The PersonRepository instance
     * @param roleRepository   The RoleRepository instance
     * @param passwordEncoder  The PasswordEncoder instance
     */
    @Autowired
    public RecruitmentService(PersonRepository personRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates a new account and saves it in the database.
     *
     * @param signUpRequest DTO containing the necessary validated fields to register an account
     * @throws Exception If the account isn't successfully created
     */
    public void createApplicant(@Valid SignUpRequest signUpRequest) throws Exception {
        if (personRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new UsernameNotUniqueException();
        }

        if (personRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailNotUniqueException();
        }

        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        Role role = roleRepository.findByName("applicant").orElseThrow(Exception::new);
        Person person = new Person(signUpRequest.getName()
                , signUpRequest.getSurname()
                , signUpRequest.getEmail()
                , signUpRequest.getSsn()
                , signUpRequest.getUsername()
                , signUpRequest.getPassword()
                , role);
        personRepository.save(person);
    }
}
