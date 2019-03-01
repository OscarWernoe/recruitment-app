package se.kth.iv1201.recruitment.payload;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class LoginRequestTest {

    private static Validator validator;

    @BeforeClass
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validRequest() {
        LoginRequest loginRequest = new LoginRequest("username", "password");
        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);
        assertThat(violations.size(), is(0));
    }

    @Test
    public void violationsFoundBlankRequest() {
        LoginRequest blankRequest = new LoginRequest("", "");
        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(blankRequest);
        assertThat(violations.size(), is(3)); // We expect three conditions to be violated (use debug to see messages)
    }
}