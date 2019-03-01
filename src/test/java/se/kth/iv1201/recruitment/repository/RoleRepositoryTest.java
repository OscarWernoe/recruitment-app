package se.kth.iv1201.recruitment.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void findByName() throws Exception {
        roleRepository.findByName("applicant").orElseThrow(Exception::new);
        roleRepository.findByName("recruiter").orElseThrow(Exception::new);
    }

}