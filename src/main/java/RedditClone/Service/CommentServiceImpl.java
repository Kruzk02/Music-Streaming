package RedditClone.Service;

import RedditClone.DTO.CommentDTO;
import RedditClone.Model.Comment;
import RedditClone.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setPosts(commentDTO.getPosts());
        comment.setUser(commentDTO.getUser());
        comment.setCreateAt(commentDTO.getCreateAt());

        return commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
