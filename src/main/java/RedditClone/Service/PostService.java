package RedditClone.Service;

import RedditClone.DTO.PostDTO;
import RedditClone.Model.Post;

import java.util.List;

public interface PostService {
    Post create(PostDTO postDTO);
    Post update(Long id,PostDTO postDTO);
    Post delete(Long id);
    Post findPostById(Long id);
    List<Post> getAllPost();
}
