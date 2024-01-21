package RedditClone.Service;

import RedditClone.DTO.SignupDTO;
import RedditClone.Model.User;

public interface UserService {

    User save(SignupDTO signupDTO);
    User findUserByEmail(String email);
    User findUserByUsername(String username);
}
