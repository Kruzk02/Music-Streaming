package RedditClone.Controller;

import RedditClone.DTO.CommentDTO;
import RedditClone.Jwt.JwtService;
import RedditClone.Model.Comment;
import RedditClone.Model.Post;
import RedditClone.Model.User;
import RedditClone.Service.CommentServiceImpl;
import RedditClone.Service.PostServiceImpl;
import RedditClone.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentServiceImpl commentService;
    private final PostServiceImpl postService;
    private final UserServiceImpl userService;
    private final JwtService jwtService;
    @Autowired
    public CommentController(CommentServiceImpl commentService, PostServiceImpl postService, UserServiceImpl userService, JwtService jwtService) {
        this.commentService = commentService;
        this.postService = postService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestBody CommentDTO commentDTO,@RequestHeader("Authorization") String authHeader){
        String token = extractToken(authHeader);
        if(token != null){
            String username = jwtService.extractUsername(token);
            User user = userService.findUserByUsername(username);
            commentDTO.setUser(user);
            commentService.save(commentDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id){
        try{
            Comment comment = commentService.delete(id);
            return ResponseEntity.ok(comment);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    private String extractToken(String authHeader){
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);
        }
        return null;
    }
}
