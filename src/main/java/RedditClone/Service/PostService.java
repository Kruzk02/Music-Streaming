package RedditClone.Service;

import RedditClone.DTO.PostDTO;
import RedditClone.Model.Post;

public interface PostService {
    Post create(PostDTO postDTO);
    Post update(Long id,PostDTO postDTO);
    void delete(Long id);
    void findPostById(Long id);
    void getAllPost();
}
