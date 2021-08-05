package danny.musicmanager.repositories;


import danny.musicmanager.entities.Song;
import danny.musicmanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository <Song,Integer> {


    List<Song> findByUser(User user);


}
