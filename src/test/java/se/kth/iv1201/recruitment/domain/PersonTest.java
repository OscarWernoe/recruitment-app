package se.kth.iv1201.recruitment.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.recruitment.repository.PersonRepository;
import se.kth.iv1201.recruitment.repository.RoleRepository;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PersonTest {
    /*
     * - Create 2 persons and make sure that id is ++
     * - Create 2 persons with the same email and make sure it doesn't work (ssn, username)
     * */

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;
    private Person person1;
    private Person person2;
    private Role role;

    @Before
    public void setup() throws Exception {
        role = roleRepository.findByName("applicant").orElseThrow(Exception::new);
        person1 = new Person("Testy", "Testerson", "test@mail.com", "1234", "testUsername", "password", role);
        person2 = new Person("Tryie", "Tryington", "try@mail.com", "5678", "tryUsername", "password", role);
        // Check that the database is empty (tests should leave a lasting effect) - must test against an empty database
        assertTrue(personRepository.findAll().isEmpty());
    }

    @Test
    public void personIdIsGenerated() {
        personRepository.save(person1);
        Optional<Person> optionalPerson1 = personRepository.findByUsername("testUsername");
        assertTrue(optionalPerson1.isPresent());
        Person fetchedPerson1 = optionalPerson1.get();
        assertThat(fetchedPerson1.getId(), is(not(0L)));
    }

    @Test
    public void personIdIncrements() {
        personRepository.save(person1);
        personRepository.save(person2);
        Optional<Person> optionalPerson1 = personRepository.findByUsername("testUsername");
        Optional<Person> optionalPerson2 = personRepository.findByUsername("tryUsername");
        assertTrue(optionalPerson1.isPresent());
        assertTrue(optionalPerson2.isPresent());
        Person fetchedPerson1 = optionalPerson1.get();
        Person fetchedPerson2 = optionalPerson2.get();
        assertEquals(fetchedPerson1.getId(), fetchedPerson2.getId() - 1);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void usernameIsUnique() {
        personRepository.save(person1);
        person2.setUsername(person1.getUsername());
        personRepository.save(person2);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void ssnIsUnique() {
        personRepository.save(person1);
        person2.setSsn(person1.getSsn());
        personRepository.save(person2);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void emailIsUnique() {
        personRepository.save(person1);
        person2.setEmail(person1.getEmail());
        personRepository.save(person2);
    }
}