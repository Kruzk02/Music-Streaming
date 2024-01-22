package RedditClone.DTO;

import RedditClone.Model.Post;
import RedditClone.Model.User;

import java.util.Date;

public class CommentDTO {

    private String content;
    private Post posts;
    private User user;

    public CommentDTO() {
    }

    public CommentDTO(String content, Post posts, User user) {
        this.content = content;
        this.posts = posts;
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPosts() {
        return posts;
    }

    public void setPosts(Post posts) {
        this.posts = posts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
