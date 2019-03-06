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
        SignUpRequest validRequest = new SignUpRequest("Test", "Test", "test@test.com", "1234", "testUsername", "testPassword");
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(validRequest);
        assertThat(violations.size(), is(0));
    }

    @Test
    public void blankRequest() {
        SignUpRequest blankRequest = new SignUpRequest("", "", "", "", "", "");
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(blankRequest);
        // password fires twice, both blank and too short, email fires on blank
        assertThat(violations.size(), is(7));
    }

    @Test
    public void invalidRequest() {
        // test that invalid email, non-numeric ssn, and short password fires (3 violations)
        SignUpRequest invalidRequest = new SignUpRequest("Test", "Test", "emailWithoutAtSymbol", "1234a", "testUsername", "short");
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(invalidRequest);
        assertThat(violations.size(), is(3));
    }
    //TODO do not include this class in commit
}