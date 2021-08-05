package danny.musicmanager.services;

import danny.musicmanager.entities.User;
import danny.musicmanager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserRepository userRepository;


    public User authenticatedUser(){

        return userRepository.findByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .orElseThrow(()-> new UsernameNotFoundException("cant find user"));
    }


}