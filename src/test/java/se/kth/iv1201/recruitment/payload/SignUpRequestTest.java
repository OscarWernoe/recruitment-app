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
public class SignUpRequestTest {

    private static Validator validator;

    @BeforeClass
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validRequest() {
        SignUpRequest validRequest = new SignUpRequest("Trialer"
                , "Trialee"
                , "trialer@trailee.com"
                , "192838", "trialerUsername"
                , "trialerPassword");
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(validRequest);
        assertThat(violations.size(), is(0));
    }

    @Test
    public void blankRequest() {
        SignUpRequest blankRequest = new SignUpRequest(""
                , ""
                , ""
                , "", ""
                , "");
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(blankRequest);
        /*
         * password fires twice, both blank and too short.
         * email on fires on blank.
         * */
        assertThat(violations.size(), is(7));
    }

    @Test
    public void invalidRequest() {
        // test that invalid email, non-numeric ssn, short password fires (3 violations)
        SignUpRequest invalidRequest = new SignUpRequest("Trialer"
                , "Trialee"
                , "emailWithoutAtSymbol"
                , "1213A", "trialerUsername"
                , "len<8");
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(invalidRequest);
        assertThat(violations.size(), is(3));
    }
}