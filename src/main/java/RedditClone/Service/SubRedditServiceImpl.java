package RedditClone.Service;

import RedditClone.DTO.SubRedditDTO;
import RedditClone.Model.SubReddit;
import RedditClone.Repository.SubRedditRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SubRedditServiceImpl implements SubRedditService{

    private final SubRedditRepository subRedditRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SubRedditServiceImpl(SubRedditRepository subRedditRepository, ModelMapper modelMapper) {
        this.subRedditRepository = subRedditRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SubReddit save(SubRedditDTO subRedditDTO) {
        SubReddit subReddit = modelMapper.map(subRedditDTO,SubReddit.class);
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
