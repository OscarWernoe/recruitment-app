package se.kth.iv1201.recruitment.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static se.kth.iv1201.recruitment.security.JwtTokenProvider.SECRET_KEY;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RecruitmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    private String requestBody = "{\"name\":\"Test\",\"surname\":\"Test\",\"email\":\"test@test.com\",\"ssn\":\"1234567890\",\"username\":\"testUsername\",\"password\":\"testPassword\"}";


    @Test
    public void register() throws Exception {
        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void authenticate() throws Exception {
        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andDo(print())
                .andExpect(status().isOk());

        MvcResult result = this.mockMvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testUsername\",\"password\":\"testPassword\"}"))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        JwtAuthenticationResponse jwtAuthenticationResponse = objectMapper.readValue(response, JwtAuthenticationResponse.class);
        String accessToken = jwtAuthenticationResponse.getAccessToken();
        Jws<Claims> jws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(accessToken);
        assertEquals("testUsername", jws.getBody().getSubject());
        assertEquals("applicant", jws.getBody().get("role"));
    }

    @Test
    public void registerUsernameExists() throws Exception {
        registerTestUser();
        String requestBodySameUsername = "{\"name\":\"TestOther\",\"surname\":\"TestOther\",\"email\":\"testOther@test.com\",\"ssn\":\"1234567890\",\"username\":\"testUsername\",\"password\":\"testPasswordOther\"}";
        String expectedMessage = "That username is already in use.";
        performRequest(requestBodySameUsername, expectedMessage);
    }

    @Test
    public void registerEmailExists() throws Exception {
        registerTestUser();
        String requestBodySameEmail = "{\"name\":\"TestOther\",\"surname\":\"TestOther\",\"email\":\"test@test.com\",\"ssn\":\"1234567890\",\"username\":\"testUsernameOther\",\"password\":\"testPasswordOther\"}";
        String expectedMessage = "An account associated to that email address already exists.";
        performRequest(requestBodySameEmail, expectedMessage);
    }

    private void performRequest(String requestBody, String expectedMessage) throws Exception {
        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andDo(print())
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value(expectedMessage));
    }

    private void registerTestUser() throws Exception {
        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
    }
}