package se.kth.iv1201.recruitment.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.recruitment.application.RecruitmentService;
import se.kth.iv1201.recruitment.domain.LoginRequest;
import se.kth.iv1201.recruitment.domain.SignUpRequest;
import se.kth.iv1201.recruitment.security.JwtTokenProvider;

import javax.validation.Valid;

/**
 * A rest controller handling all incoming HTTP requests to the application.
 */
@RestController
@CrossOrigin
public class RecruitmentController {

    private final RecruitmentService service;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    /**
     * Creates an instance of the controller with a specified RecruitmentService linked to it.
     *
     * @param service               The RecruitmentService instance.
     * @param authenticationManager The AuthenticationManager instance.
     * @param tokenProvider         The JwtTokenProvider instance.
     */
    @Autowired
    public RecruitmentController(RecruitmentService service, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    /**
     * Handles applicant registration requests.
     *
     * @param signUpRequest DTO containing the necessary validated fields to register an account.
     * @return JSON response with indication of the result of the account creation.
     */
    @PostMapping("/users")
    public void register(@Valid @RequestBody SignUpRequest signUpRequest) throws Exception {
        this.service.createApplicant(signUpRequest);
    }

    /**
     * @param loginRequest DTO containing the necessary validated fields to login in to an account.
     * @return JSON response.
     */
    @PostMapping("/session")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(new Response(true, null));
    }
}
