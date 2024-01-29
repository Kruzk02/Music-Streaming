package RedditClone.Model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Date;

@Entity
public class SubReddit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Post> posts;

    private Date createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public SubReddit() {
    }

    public SubReddit(Long id, String name, String description, Collection<Post> posts, Date createAt, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.posts = posts;
        this.createAt = createAt;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
