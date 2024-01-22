package RedditClone.Controller;

import RedditClone.DTO.PostDTO;
import RedditClone.Model.Post;
import RedditClone.Service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostServiceImpl postService;

    @Autowired
    public PostController(PostServiceImpl postService) {
        this.postService = postService;
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
    public ResponseEntity<Post> createPosts(@RequestBody PostDTO postDTO){
        Post post = postService.create(postDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
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
}
