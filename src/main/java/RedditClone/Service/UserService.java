package RedditClone.Service;

import RedditClone.DTO.LoginDTO;
import RedditClone.DTO.SignupDTO;
import RedditClone.Model.User;

public interface UserService {

    User save(SignupDTO signupDTO);
    User findUserByEmail(String email);
    User findUserByUsername(String username);
    String login(LoginDTO loginDTO);
}
