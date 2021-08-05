package danny.musicmanager.repositories;


import danny.musicmanager.entities.Album;
import danny.musicmanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository <Album,Integer> {


    List<Album> findByUser(User user);
}
