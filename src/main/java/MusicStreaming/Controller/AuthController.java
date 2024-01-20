package MusicStreaming.Controller;

import MusicStreaming.DTO.LoginDTO;
import MusicStreaming.DTO.SignupDTO;
import MusicStreaming.Jwt.JwtService;
import MusicStreaming.Model.User;
import MusicStreaming.Model.VerificationToken;
import MusicStreaming.Service.EmailService;
import MusicStreaming.Service.UserServiceImpl;
import MusicStreaming.Service.VerificationTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserServiceImpl userService;
    private final VerificationTokenService verificationTokenService;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(UserServiceImpl userService, VerificationTokenService verificationTokenService, EmailService emailService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.verificationTokenService = verificationTokenService;
        this.emailService = emailService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDTO signupDTO){
        User existingEmail = userService.findUserByEmail(signupDTO.getEmail());
        logger.info("Registration attempt for username : {}",signupDTO.getUsername());
        if(existingEmail != null){
            logger.warn("Registration failed. Email '{}' is already taken.", signupDTO.getEmail());
            return ResponseEntity.badRequest().body("Email is already taken");
        }

        User saveduser = userService.save(signupDTO);
        VerificationToken verificationToken = verificationTokenService.generateVerificationToken(saveduser);
        emailService.sendVerificationEmail(saveduser.getEmail(),verificationToken.getToken());
        logger.info("User '{}' registered successfully.Check your email for verification.",saveduser.getEmail());
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyAccount(@RequestParam String token) {
        try {
            verificationTokenService.verifyAccount(token);
            return ResponseEntity.ok("Account verified successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token verification failed or expired");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(),loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtService.generateToken(loginDTO.getUsername());
            Map<String,String> response = new HashMap<>();
            response.put("token",token);

            logger.info("User '{}' logged in successfully.", loginDTO.getUsername());
            return ResponseEntity.ok(response);
        }catch (Exception e){
            logger.warn("Login failed for username: {}", loginDTO.getUsername());

            return ResponseEntity.status(401).body("Invalid username or password.");
        }
    }
}
