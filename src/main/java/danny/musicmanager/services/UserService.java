package danny.musicmanager.services;

import danny.musicmanager.entities.User;
import danny.musicmanager.exceptions.UserNotFoundException;
import danny.musicmanager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }


    public User createUser(User user) {
        return userRepository.save(user);

    }

    public List<User> findByMusicId(int id) {
        List<User> user = userRepository.findBySongId(id);
        return user;
    }


    public List<User> findAllUsers() {
        return userRepository.findAll();

    }

    public List<User> findUserByOccupation(String occupation) {

        List<User> user = userRepository.findByOccupation(occupation);
        return user;

    }
    public Optional<User> findUserById(int id){
        Optional<User> user = Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(" user with this id " + id + " does not exist ")));
        return user;

    }

    public void deleteUser(int id){
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
        else {
            throw new UserNotFoundException("User not found with id " + id + " try another userId ");
        }

    }


    public User updateUser(int id,User user){
    userRepository.findById(id);
        return userRepository.save(user);

    }
}
