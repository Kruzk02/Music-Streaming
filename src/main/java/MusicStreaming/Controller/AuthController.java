package MusicStreaming.Controller;

import MusicStreaming.DTO.LoginDTO;
import MusicStreaming.DTO.SignupDTO;
import MusicStreaming.Jwt.JwtService;
import MusicStreaming.Model.User;
import MusicStreaming.Service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserServiceImpl userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(UserServiceImpl userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
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
        logger.info("User {} registration successfully.",saveduser);
        return ResponseEntity.ok("User registered successfully");
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
