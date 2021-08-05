package danny.musicmanager.repositories;


import danny.musicmanager.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository <Comment,Integer> {


    List<Comment> findBySong(Song song);

    List<Comment> findByAlbum(Album album);

    List<Comment> findByUser(User user);
}
