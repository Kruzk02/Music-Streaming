package RedditClone.DTO;

import RedditClone.Model.Comment;
import RedditClone.Model.User;

import java.util.Collection;
import java.util.Date;

public class PostDTO {

    private String title;
    private String content;
    private Collection<Comment> comments;
    private User user;
    private String subReddit;

    public PostDTO() {
    }

    public PostDTO(String title, String content, Collection<Comment> comments, User user,String subReddit) {
        this.title = title;
        this.content = content;
        this.comments = comments;
        this.user = user;
        this.subReddit = subReddit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSubReddit() {
        return subReddit;
    }

    public void setSubReddit(String subReddit) {
        this.subReddit = subReddit;
    }
}
