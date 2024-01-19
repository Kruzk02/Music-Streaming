package MusicStreaming.Service;

import MusicStreaming.DTO.SignupDTO;
import MusicStreaming.Model.User;

public interface UserService {

    User save(SignupDTO signupDTO);
    User findUserByEmail(String email);
    User findUserByUsername(String username);
}
