package se.kth.iv1201.recruitment.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import se.kth.iv1201.recruitment.security.UserDetailsImpl;

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
     * @param service               The RecruitmentService instance
     * @param authenticationManager The AuthenticationManager instance
     * @param tokenProvider         The JwtTokenProvider instance
     */
    @Autowired
    public RecruitmentController(RecruitmentService service, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    /**
     * Handles applicant registration requests.
     * If any exception is thrown due to this method's invocation, then it will be caught and handled by the
     * {@code RestResponseEntityExceptionHandler} class.
     *
     * @param signUpRequest DTO containing the necessary validated fields to register an account
     * @return JSON response with relevant message
     */
    @PostMapping("/users")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpRequest signUpRequest) throws Exception {
        this.service.createApplicant(signUpRequest);
        return ResponseEntity.ok(new Response(true, "Successfully registered applicant."));
    }


    // TODO: is comment accurate?
    /**
     * Generates an token for authentication to the requesting client.
     *
     * @param loginRequest DTO containing the necessary validated fields to login in to an account
     * @return JSON response with JWT access token
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

    /**
     * Saves an application to the database. Only available for clients with applicant-status.
     *
     * @return JSON response with relevant message
     */
    @PostMapping("/applications")
    public ResponseEntity<?> apply() {
        if (isApplicant()) {
            return ResponseEntity.ok(new Response(true, "Successfully saved application."));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Fetches all available applications. Only available for clients with recruiter-status.
     * @return JSON response with relevant message
     */
    @GetMapping("/applications")
    public ResponseEntity<?> listApplications() {
        if (isRecruiter()) {
            return ResponseEntity.ok(new Response(true, null));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Shows a specified application. Only available for clients with recruiter-status.
     *
     * @param id The id of the desired application
     * @return JSON response with relevant message
     */
    @GetMapping("/applications/{id}")
    public ResponseEntity<?> showApplication(@PathVariable Long id) {
        if (isRecruiter()) {
            return ResponseEntity.ok(new Response(true, id.toString()));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    private boolean isRecruiter() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getRole().equals("recruiter");
    }

    private boolean isApplicant() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getRole().equals("applicant");
    }
}
