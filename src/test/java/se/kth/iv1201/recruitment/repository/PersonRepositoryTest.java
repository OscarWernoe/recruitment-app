package se.kth.iv1201.recruitment.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.recruitment.domain.Person;
import se.kth.iv1201.recruitment.domain.Role;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void save() throws Exception {
        final long countBefore = personRepository.count();
        Person person = getPerson();
        personRepository.save(person);
        final long countAfter = personRepository.count();
        assertTrue(countAfter > countBefore);
    }

    private Person getPerson() throws Exception {
        Role role = roleRepository.findByName("applicant").orElseThrow(Exception::new);
        return new Person("Test", "Test", "test@test.com", "1234", "testUsername", "testPassword", role);
    }

    @Test
    public void findByUsername() throws Exception {
        Person person = getPerson();
        personRepository.save(person);
        Person actual = personRepository.findByUsername(person.getUsername()).orElseThrow(Exception::new);
        assertEquals(person, actual);
    }

    // TODO test existByUsername()
    // TODO test existByEmail()

}