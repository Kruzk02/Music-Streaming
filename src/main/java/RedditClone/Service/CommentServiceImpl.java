package RedditClone.Service;

import RedditClone.DTO.CommentDTO;
import RedditClone.Model.Comment;
import RedditClone.Repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Comment save(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO,Comment.class);
        comment.setCreateAt(new Date(System.currentTimeMillis()));

        return commentRepository.save(comment);
    }

    @Override
    public Comment delete(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if(comment != null){
            commentRepository.delete(comment);
        }
        return null;
    }
}
