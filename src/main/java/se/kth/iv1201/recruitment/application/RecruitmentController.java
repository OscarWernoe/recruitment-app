package se.kth.iv1201.recruitment.application;

import org.springframework.web.bind.annotation.RestController;
import se.kth.iv1201.recruitment.repository.AccountRepository;

@RestController
public class RecruitmentController {

    private final AccountRepository accountRepo;

    public RecruitmentController(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }


}
