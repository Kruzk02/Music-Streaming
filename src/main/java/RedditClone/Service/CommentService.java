package RedditClone.Service;


import RedditClone.DTO.CommentDTO;
import RedditClone.Model.Comment;

public interface CommentService {
    Comment save(CommentDTO commentDTO);
    Comment delete(Long id);
}
