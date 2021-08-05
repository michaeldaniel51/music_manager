package danny.musicmanager.services;


import danny.musicmanager.entities.Comment;
import danny.musicmanager.entities.Reaction;
import danny.musicmanager.entities.Song;
import danny.musicmanager.entities.User;
import danny.musicmanager.exceptions.SongNotOutDatedException;
import danny.musicmanager.repositories.CommentRepository;
import danny.musicmanager.repositories.ReactionRepository;
import danny.musicmanager.repositories.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;
    private final SecurityService securityService;
    private final CommentRepository commentRepository;
    private final ReactionRepository reactionRepository;


    public Song addSong(Song song) {
        song.setUser(securityService.authenticatedUser());
        return songRepository.save(song);
    }



    public Song commentOnSong(int id, Comment comment){

        Song song = findSongById(id);
        comment.setSong(song);
        comment.setUser(securityService.authenticatedUser());
        commentRepository.save(comment);
        return songRepository.save(song);

    }


    public Song reactionOnSong(int id, Reaction reaction){

        Song song = findSongById(id);
        reaction.setSong(song);
        reaction.setUser(securityService.authenticatedUser());
        reactionRepository.save(reaction);
        return songRepository.save(song);

    }

    public boolean isOutdated(Song song) {
        return !song.getDateReleased().plusMonths(4).isAfter(LocalDateTime.now());
    }

    public void deleteOutDatedSongById(int songId) {
        Song song = songRepository.findById(songId).orElseThrow();
        if (isOutdated(song)) {
            songRepository.deleteById(song.getId());
        } else {
            throw new SongNotOutDatedException("music is not outdated");
        }
    }

    public void deleteSongById(int id){

        songRepository.deleteById(id);

    }

    public List<Song> findAllSong(){
        List<Song> song = songRepository.findAll().stream().collect(Collectors.toList());
        return song;
    }

    public List<Song> findUser(){
        User user = securityService.authenticatedUser();
        return songRepository.findByUser(user);

    }
    public Song findSongById(int id) {
        return songRepository.findById(id).orElseThrow();
    }

}
