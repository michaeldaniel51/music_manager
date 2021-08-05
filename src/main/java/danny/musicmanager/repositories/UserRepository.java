package danny.musicmanager.repositories;


import danny.musicmanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    Optional<User> findByUsername(String username);

    List<User> findByOccupation (String occupation);

    List<User> findBySongId (int id);
}
