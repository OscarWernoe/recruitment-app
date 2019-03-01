package se.kth.iv1201.recruitment.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.recruitment.repository.RoleRepository;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RoleTest {

    @Autowired
    private RoleRepository roleRepository;
    private Role applicant;
    private Role recruiter;

    @Test
    public void entitiesExistInDb() {
        Optional<Role> applicantOptional = roleRepository.findByName("applicant");
        Optional<Role> recruiterOptional = roleRepository.findByName("recruiter");
        assertTrue(applicantOptional.isPresent());
        assertTrue(recruiterOptional.isPresent());
        applicant = applicantOptional.get();
        recruiter = recruiterOptional.get();
    }


}