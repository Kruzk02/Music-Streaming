package RedditClone.Service;

import RedditClone.DTO.VoteDTO;
import RedditClone.Model.Post;
import RedditClone.Model.User;
import RedditClone.Model.Vote;
import RedditClone.Model.VoteType;
import RedditClone.Repository.PostRepository;
import RedditClone.Repository.UserRepository;
import RedditClone.Repository.VoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public VoteService(VoteRepository voteRepository, PostRepository postRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void upVote(VoteDTO voteDTO){
        Post post = postRepository.findById(voteDTO.getPostId()).orElse(null);
        User user = userRepository.findById(voteDTO.getUserId()).orElse(null);

        Vote vote = new Vote();
        vote.setPost(post);
        vote.setUser(user);
        vote.setVoteType(VoteType.UPVOTE);
        voteRepository.save(vote);
    }

    @Transactional
    public void downVote(VoteDTO voteDTO){
        Post post = postRepository.findById(voteDTO.getPostId()).orElse(null);
        User user = userRepository.findById(voteDTO.getUserId()).orElse(null);

        Vote vote = new Vote();
        vote.setPost(post);
        vote.setUser(user);
        vote.setVoteType(VoteType.DOWNVOTE);
        voteRepository.save(vote);
    }
}
