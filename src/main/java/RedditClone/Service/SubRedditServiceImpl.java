package RedditClone.Service;

import RedditClone.DTO.SubRedditDTO;
import RedditClone.Model.SubReddit;
import RedditClone.Repository.SubRedditRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SubRedditServiceImpl implements SubRedditService{

    private final SubRedditRepository subRedditRepository;

    public SubRedditServiceImpl(SubRedditRepository subRedditRepository) {
        this.subRedditRepository = subRedditRepository;
    }

    @Override
    public SubReddit save(SubRedditDTO subRedditDTO) {
        SubReddit subReddit = new SubReddit();
        subReddit.setName(subRedditDTO.getName());
        subReddit.setPosts(subRedditDTO.getPosts());
        subReddit.setDescription(subRedditDTO.getDescription());
        subReddit.setUser(subRedditDTO.getUser());
        subReddit.setCreateAt(new Date(System.currentTimeMillis()));
        return subRedditRepository.save(subReddit);
    }

    @Override
    public List<SubReddit> getAll() {
        return subRedditRepository.findAll();
    }

    @Override
    public SubReddit getById(Long id) {
        return subRedditRepository.findById(id).orElse(null);
    }
}
