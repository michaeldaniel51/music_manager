package danny.musicmanager.services;


import danny.musicmanager.entities.Comment;
import danny.musicmanager.entities.User;
import danny.musicmanager.repositories.AlbumRepository;
import danny.musicmanager.repositories.CommentRepository;
import danny.musicmanager.repositories.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {


    private final CommentRepository commentRepository;
    private final SecurityService securityService;
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;

    public Comment addComment (Comment comment){
        comment.setUser(securityService.authenticatedUser());
        return commentRepository.save(comment);


    }

    public void deleteComment(int id){

        commentRepository.deleteById(id);

    }

    public List<Comment> findCommentBySong(int songId) {
        return commentRepository.findBySong(songRepository.findById(songId).orElseThrow());
    }


    public List<Comment> findCommentOnAlbum(int albumId){

        return commentRepository.findByAlbum(albumRepository.findById(albumId).orElseThrow());

    }


    public List<Comment> findAllComment(){

        return commentRepository.findAll();

    }

    public Comment findById(int id){

        return commentRepository.findById(id).get();

    }

    public List<Comment>findUser(){

        User user = securityService.authenticatedUser();
        return commentRepository.findByUser(user);
    }
}
