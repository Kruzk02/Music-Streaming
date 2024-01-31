package RedditClone.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @OneToMany(mappedBy = "posts")
    private Collection<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subReddit_id",referencedColumnName = "id")
    private SubReddit subReddit;

    public Post() {
    }

    public Post(Long id, String title, String content, Collection<Comment> comments, User user, Date createAt,SubReddit subReddit) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.comments = comments;
        this.user = user;
        this.createAt = createAt;
        this.subReddit = subReddit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public SubReddit getSubReddit() {
        return subReddit;
    }

    public void setSubReddit(SubReddit subReddit) {
        this.subReddit = subReddit;
    }
}
