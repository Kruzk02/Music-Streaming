package RedditClone.Controller;

import RedditClone.DTO.SubRedditDTO;
import RedditClone.Model.SubReddit;
import RedditClone.Service.SubRedditServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/subreddit")
public class SubRedditController {

    private final SubRedditServiceImpl subRedditService;

    @Autowired
    public SubRedditController(SubRedditServiceImpl subRedditService) {
        this.subRedditService = subRedditService;
    }

    @PostMapping
    public ResponseEntity<SubReddit> create(@RequestBody SubRedditDTO subRedditDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subRedditService.save(subRedditDTO));
    }

    @GetMapping
    public ResponseEntity<Collection<SubReddit>> getAllSubReddit(){
        return ResponseEntity.status(HttpStatus.OK).body(subRedditService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubReddit> getSubReddit(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(subRedditService.getById(id));
    }

    private String extractToken(String authHeader){
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);
        }
        return null;
    }
}
