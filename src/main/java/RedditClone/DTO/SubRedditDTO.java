package RedditClone.DTO;

import RedditClone.Model.Post;
import RedditClone.Model.User;

import java.util.Collection;

public class SubRedditDTO {

    private String name;
    private String description;
    private Collection<Post> posts;
    private User user;

    public SubRedditDTO() {
    }

    public SubRedditDTO(String name, String description, Collection<Post> posts,User user) {
        this.name = name;
        this.description = description;
        this.posts = posts;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
