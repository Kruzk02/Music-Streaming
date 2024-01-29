package RedditClone.Repository;

import RedditClone.Model.SubReddit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubRedditRepository extends JpaRepository<SubReddit,Long> {
    SubReddit findByName(String name);
}
