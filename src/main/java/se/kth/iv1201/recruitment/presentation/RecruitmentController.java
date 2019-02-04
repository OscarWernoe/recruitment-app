package se.kth.iv1201.recruitment.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.recruitment.application.RecruitmentService;
import se.kth.iv1201.recruitment.domain.Person;

import java.util.Collections;
import java.util.Map;

@RestController
public class RecruitmentController {

    private final RecruitmentService service;

    @Autowired
    public RecruitmentController(RecruitmentService service) {
        this.service = service;
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Map createAccount(@RequestBody Person person) {
        try {
            this.service.addPerson(person);
        } catch (Exception e) {
            Collections.singletonMap("response", "failure");
        }
        return Collections.singletonMap("response", "success");
    }
}
