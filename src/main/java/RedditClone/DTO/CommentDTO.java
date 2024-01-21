package RedditClone.DTO;

import RedditClone.Model.Post;
import RedditClone.Model.User;

import java.util.Date;

public class CommentDTO {

    private String content;
    private Post posts;
    private User user;
    private Date createAt;

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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
