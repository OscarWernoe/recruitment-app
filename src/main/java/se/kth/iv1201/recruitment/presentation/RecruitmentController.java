package se.kth.iv1201.recruitment.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.recruitment.application.RecruitmentService;
import se.kth.iv1201.recruitment.domain.AccountDTO;

import java.util.Collections;
import java.util.Map;

@RestController
public class RecruitmentController {

    private final RecruitmentService service;

    @Autowired
    public RecruitmentController(RecruitmentService service) {
        this.service = service;
    }

    @CrossOrigin
    @PostMapping(value = "/users")
    public @ResponseBody
    Map createAccount(@RequestBody AccountDTO accountDTO) {
        try {
            this.service.createAccount(accountDTO);
        } catch (Exception e) {
            return Collections.singletonMap("response", "failure");
        }
        return Collections.singletonMap("response", "success");
    }
}
