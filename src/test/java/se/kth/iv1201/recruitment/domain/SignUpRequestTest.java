package se.kth.iv1201.recruitment.domain;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SignUpRequestTest {

    private static Validator validator;

    @BeforeClass
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validRequest() {
        SignUpRequest validRequest = new SignUpRequest("Test", "Test", "test@test.com", "1234567891", "testUsername", "testPassword");
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(validRequest);
        assertThat(violations.size(), is(0));
    }

    @Test
    public void blankRequest() {
        SignUpRequest blankRequest = new SignUpRequest("", "", "", "", "", "");
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(blankRequest);
        assertThat(violations.size(), is(10));
    }

    @Test
    public void invalidRequestConcerningChar() {
        // test that invalid email and non-numeric ssn fires 2 violations.
        SignUpRequest invalidRequest = new SignUpRequest("Test", "Test", "emailWithoutAtSymbol", "123456789a", "testUsername", "mediumLength");
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(invalidRequest);
        assertThat(violations.size(), is(2));
    }

    @Test
    public void invalidRequestConcerningMin() {
        // test that too short length of name, surname, ssn and password fires 4 violations.
        SignUpRequest invalidRequest = new SignUpRequest("T", "V", "email@correct.com", "123456789", "testuser", "short");
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(invalidRequest);
        assertThat(violations.size(), is(4));
    }

    @Test
    public void invalidRequestConcerningMax() {
        // test that too long length of name, surname, email, ssn, username and password fires 6 violations.
        SignUpRequest invalidRequest = new SignUpRequest("TestTestTestTestTestTestTestTestTestTestTestTestTest", "TestTestTestTestTestTestTestTestTestTestTestTestTest", "email@correct.com.TestTestTestTestTestTestTestTestTestTestTestTestTest", "12345678910", "testuserTestTestTestTestTestTestTestTestTestTestTestTestTest", "UltraShortPasswordUltraShortPasswordUltraShortPasswordUltraShortPasswordUltraShortPasswordUltraShortPassword");
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(invalidRequest);
        assertThat(violations.size(), is(6));
    }
    //TODO do not include this class in commit
}