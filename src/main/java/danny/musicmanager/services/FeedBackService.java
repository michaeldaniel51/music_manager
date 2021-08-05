package danny.musicmanager.services;

import danny.musicmanager.entities.FeedBack;
import danny.musicmanager.entities.Reaction;
import danny.musicmanager.entities.User;
import danny.musicmanager.repositories.FeedBackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedBackService {

    private final FeedBackRepository feedBackRepository;
    private final SecurityService securityService;


    public FeedBack addFeedBack(FeedBack feedBack) {
        feedBack.setUser(securityService.authenticatedUser());
        return feedBackRepository.save(feedBack);
    }


    public void deleteFeedBackById(int id){

        feedBackRepository.deleteById(id);

    }

    public List<FeedBack> findAllFeedBack(){
        List<FeedBack> feedBacks = feedBackRepository.findAll().stream().collect(Collectors.toList());
        return feedBacks;
    }

    public List<FeedBack> findUser(){
        User user = securityService.authenticatedUser();
        return feedBackRepository.findByUser(user);

    }
    public FeedBack findReactionById(int id) {
        return feedBackRepository.findById(id).orElseThrow();
    }

}
