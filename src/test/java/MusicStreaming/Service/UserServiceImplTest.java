package MusicStreaming.Service;

import MusicStreaming.DTO.SignupDTO;
import MusicStreaming.Model.Role;
import MusicStreaming.Model.User;
import MusicStreaming.Repository.RoleRepository;
import MusicStreaming.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//I Suck at Junit test
class UserServiceImplTest {

    @Mock private UserRepository userRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private RoleRepository roleRepository;
    @InjectMocks private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save_shouldSaveUser() {
        SignupDTO signupDTO = new SignupDTO();
        signupDTO.setUsername("testUser");
        signupDTO.setEmail("test@example.com");
        signupDTO.setPassword("testPassword");

        User expectedUser = new User();
        expectedUser.setUsername("testUser");
        expectedUser.setEmail("test@example.com");
        expectedUser.setPassword("encodedPassword");
        expectedUser.setRoles(Collections.singletonList(new Role("ROLE_USER")));

        when(roleRepository.findByName("ROLE_USER")).thenReturn(new Role("ROLE_USER"));
        when(passwordEncoder.encode("testPassword")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);

        User savedUser = userService.save(signupDTO);

        assertEquals("testUser", savedUser.getUsername());
        assertEquals("test@example.com", savedUser.getEmail());
        assertEquals("encodedPassword", savedUser.getPassword());

        verify(roleRepository, times(1)).findByName("ROLE_USER");
        verify(passwordEncoder, times(1)).encode("testPassword");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void findUserByEmail_shouldReturnUser() {
        String email = "test@example.com";
        User expectedUser = new User();
        when(userRepository.findByEmail(email)).thenReturn(expectedUser);

        User foundUser = userService.findUserByEmail(email);

        assertEquals(expectedUser, foundUser);

        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void findUserByUsername_shouldReturnUser() {
        String username = "testUser";
        User expectedUser = new User();
        when(userRepository.findByUsername(username)).thenReturn(expectedUser);

        User foundUser = userService.findUserByUsername(username);

        assertEquals(expectedUser, foundUser);

        verify(userRepository, times(1)).findByUsername(username);
    }
}
