package RedditClone.Service;

import RedditClone.DTO.PostDTO;
import RedditClone.Model.Post;
import RedditClone.Repository.CommentRepository;
import RedditClone.Repository.PostRepository;
import RedditClone.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post create(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setComments(postDTO.getComments());
        post.setUser(postDTO.getUser());
        post.setCreateAt(postDTO.getCreateAt());

        return postRepository.save(post);
    }

    @Override
    public Post update(Long id,PostDTO postDTO) {
        Post post = postRepository.findById(id).orElse(null);
        if(post != null){
            post.setTitle(postDTO.getTitle());
            post.setContent(postDTO.getContent());

            return postRepository.save(post);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public void findPostById(Long id) {
        postRepository.findById(id);
    }

    @Override
    public void getAllPost() {
        postRepository.findAll();
    }
}
