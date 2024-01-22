package RedditClone.Controller;

import RedditClone.DTO.PostDTO;
import RedditClone.Jwt.JwtService;
import RedditClone.Model.Post;
import RedditClone.Model.User;
import RedditClone.Service.PostServiceImpl;
import RedditClone.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostServiceImpl postService;
    private final JwtService jwtService;
    private final UserServiceImpl userService;
    @Autowired
    public PostController(PostServiceImpl postService, JwtService jwtService, UserServiceImpl userService) {
        this.postService = postService;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> posts = postService.getAllPost();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id){
        try {
            Post post = postService.findPostById(id);
            return ResponseEntity.ok(post);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPosts(@RequestBody PostDTO postDTO,@RequestHeader("Authorization") String authHeader){
        String token = extractToken(authHeader);

        if(token != null){
            String username = jwtService.extractUsername(token);
            User user = userService.findUserByUsername(username);

            postDTO.setUser(user);
            postService.create(postDTO);
            return new  ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Post> updatePosts(@RequestBody PostDTO postDTO,@PathVariable Long id){
        try {
            Post post = postService.update(id, postDTO);
            return ResponseEntity.ok(post);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Post> deletePosts(@PathVariable Long id){
        try {
            Post post = postService.delete(id);
            return ResponseEntity.ok(post);
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
