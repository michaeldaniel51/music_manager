package danny.musicmanager.services;


import danny.musicmanager.entities.Comment;
import danny.musicmanager.entities.Reaction;
import danny.musicmanager.entities.Song;
import danny.musicmanager.entities.User;
import danny.musicmanager.exceptions.SongNotOutDatedException;
import danny.musicmanager.repositories.ReactionRepository;
import danny.musicmanager.repositories.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReactionService {


    private final ReactionRepository reactionRepository;
    private final SecurityService securityService;
    private final SongRepository songRepository;

    public Reaction addReaction(Reaction reaction) {
        reaction.setUser(securityService.authenticatedUser());
        return reactionRepository.save(reaction);
    }


    public void deleteReactionById(int id){

        reactionRepository.deleteById(id);

    }

    public List<Reaction> findAllReaction(){
        List<Reaction> reaction = reactionRepository.findAll().stream().collect(Collectors.toList());
        return reaction;
    }

    public List<Reaction> findUser(){
        User user = securityService.authenticatedUser();
        return reactionRepository.findByUser(user);

    }
    public Reaction findReactionById(int id) {
        return reactionRepository.findById(id).orElseThrow();
    }


    public List<Reaction> findReactionBySong(int id){

       return reactionRepository.findBySong(songRepository.findById(id).orElseThrow());


    }

}
