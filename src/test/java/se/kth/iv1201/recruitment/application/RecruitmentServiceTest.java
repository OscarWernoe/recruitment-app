package se.kth.iv1201.recruitment.application;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.recruitment.domain.SignUpRequest;
import se.kth.iv1201.recruitment.presentation.EmailNotUniqueException;
import se.kth.iv1201.recruitment.presentation.UsernameNotUniqueException;
import se.kth.iv1201.recruitment.repository.PersonRepository;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RecruitmentServiceTest {

    private static SignUpRequest signUpRequest;

    @Autowired
    private RecruitmentService service;

    @Autowired
    private PersonRepository personRepository;

    @BeforeClass
    public static void setup() {
        signUpRequest = new SignUpRequest("Test"
                , "Test"
                , "test@test.com"
                , "1234", "testUsername"
                , "testPassword");
    }

    @Test
    public void createApplicant() throws Exception {
        final long countBefore = personRepository.count();
        service.createApplicant(signUpRequest);
        final long countAfter = personRepository.count();
        assertThat(countAfter, is(greaterThan(countBefore)));
    }

    @Test(expected = UsernameNotUniqueException.class)
    public void createApplicantUsernameExists() throws Exception {
        service.createApplicant(signUpRequest);
        SignUpRequest sameUsernameRequest = new SignUpRequest("TestOther"
                , "TestOther"
                , "testOther@test.com"
                , "0987", "testUsername"
                , "testPasswordOther");
        service.createApplicant(sameUsernameRequest);
    }

    @Test(expected = EmailNotUniqueException.class)
    public void createApplicantEmailExists() throws Exception {
        service.createApplicant(signUpRequest);
        SignUpRequest sameEmailRequest = new SignUpRequest("TestOther"
                , "TestOther"
                , "test@test.com"
                , "0987", "testUsernameOther"
                , "testPasswordOther");
        service.createApplicant(sameEmailRequest);
    }
}