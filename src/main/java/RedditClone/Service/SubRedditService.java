package RedditClone.Service;

import RedditClone.DTO.SubRedditDTO;
import RedditClone.Model.SubReddit;

import java.util.List;

public interface SubRedditService {

    SubReddit save(SubRedditDTO subRedditDTO);
    List<SubReddit> getAll();
    SubReddit getById(Long id);
}
