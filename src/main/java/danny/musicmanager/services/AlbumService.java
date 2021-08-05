package danny.musicmanager.services;


import danny.musicmanager.entities.*;
import danny.musicmanager.repositories.AlbumRepository;
import danny.musicmanager.repositories.CommentRepository;
import danny.musicmanager.repositories.ReactionRepository;
import danny.musicmanager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {


    private final AlbumRepository albumRepository;
    private final SecurityService securityService;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ReactionRepository reactionRepository;

    public Album saveAlbum (Album album){
        album.setUser(securityService.authenticatedUser());
        return albumRepository.save(album);

    }

    public void deleteAlbum(int id){
        albumRepository.deleteById(id);

    }


    public Album CommentOnAlbum(int id, Comment comment){

        Album album = findAlbumById(id);
        comment.setAlbum(album);
        comment.setUser(securityService.authenticatedUser());
        commentRepository.save(comment);
        return albumRepository.save(album);


    }
    public Album reactionOnAlbum(int id, Reaction reaction){

        Album album = findAlbumById(id);
        reaction.setAlbum(album);
        reaction.setUser(securityService.authenticatedUser());
        reactionRepository.save(reaction);
        return albumRepository.save(album);

    }



    public List<Album> findAllAlbum(){

        return albumRepository.findAll();

    }


    public List<Album> findUser(){

        User user = securityService.authenticatedUser();
        return albumRepository.findByUser(user);

    }

    public Album findAlbumById(int id){

        return albumRepository.findById(id).get();

    }



}
