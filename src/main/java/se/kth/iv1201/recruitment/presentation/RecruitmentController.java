package se.kth.iv1201.recruitment.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.kth.iv1201.recruitment.application.RecruitmentService;
import se.kth.iv1201.recruitment.domain.AccountDTO;

import java.util.Collections;
import java.util.Map;

/**
 * A rest controller handling the HTTP requests of the recruitment process.
 */
@RestController
public class RecruitmentController {

    private final RecruitmentService service;

    /**
     * Creates an instance of the controller with a specified RecruitmentService linked to it.
     *
     * @param service The RecruitmentService instance
     */
    @Autowired
    public RecruitmentController(RecruitmentService service) {
        this.service = service;
    }

    /**
     * Handling the create account request.
     * @param accountDTO The data transfer object for Person.
     * @return JSON response with indication of the result of the account creation.
     */
    @CrossOrigin
    @PostMapping(value = "/users")
    public Map createAccount(@RequestBody AccountDTO accountDTO) {
        try {
            this.service.createAccount(accountDTO);
        } catch (Exception e) {
            return Collections.singletonMap("response", "failure");
        }
        return Collections.singletonMap("response", "success");
    }
}
