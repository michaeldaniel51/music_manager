package danny.musicmanager.repositories;

import danny.musicmanager.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionRepository extends JpaRepository <Reaction,Integer> {



    List<Reaction> findBySong(Song song);


    List<Reaction> findByUser(User user);
}
