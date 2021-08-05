package danny.musicmanager.repositories;


import danny.musicmanager.entities.FeedBack;
import danny.musicmanager.entities.Reaction;
import danny.musicmanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedBackRepository extends JpaRepository <FeedBack,Integer> {


    List<FeedBack> findByUser(User user);
}
