package se.kth.iv1201.recruitment.presentation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RecruitmentControllerTest {

    /*
     * Request body (success):
     *
     *   {"name":"Test","surname":"Testerson","email":"test@email.com","ssn":"12345678980","username":"testUsername","password":"testPassword"}
     *
     * Success response:
     *
     *   {"success":true,"message":null}
     *
     * Failure response: (email not unique)
     *
     *   {"success":false,"message":null}
     *
     * */

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void register() throws Exception {
        String requestBody = "{\"name\":\"Test\",\"surname\":\"Testerson\",\"email\":\"test@email.com\",\"ssn\":\"12345678980\",\"username\":\"testUsername\",\"password\":\"testPassword\"}";
        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void authenticate() {
        // Todo: how can we determine success or failure
    }
}