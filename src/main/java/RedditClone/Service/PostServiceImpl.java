package RedditClone.Service;

import RedditClone.DTO.PostDTO;
import RedditClone.Model.Post;
import RedditClone.Model.SubReddit;
import RedditClone.Repository.PostRepository;
import RedditClone.Repository.SubRedditRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final SubRedditRepository subRedditRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, SubRedditRepository subRedditRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.subRedditRepository = subRedditRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Post create(PostDTO postDTO) {
        SubReddit subReddit = subRedditRepository.findByName(postDTO.getSubReddit());

        Post post = modelMapper.map(postDTO,Post.class);
        post.setCreateAt(new Date(System.currentTimeMillis()));
        post.setSubReddit(subReddit);

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
    public Post delete(Long id) {
        Post post = postRepository.findById(id).orElse(null);

        if(post != null){
            postRepository.delete(post);
        }
        return null;
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }
}
