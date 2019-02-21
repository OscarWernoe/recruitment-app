package se.kth.iv1201.recruitment.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.recruitment.domain.Role;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void findByName() throws Exception {
        Role applicant = roleRepository.findByName("applicant").orElseThrow(Exception::new);
        Role recruiter = roleRepository.findByName("recruiter").orElseThrow(Exception::new);
    }

}