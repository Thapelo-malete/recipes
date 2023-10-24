package tech.thapelomalifi.recipes.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.thapelomalifi.recipes.model.AppUserDetails;
import tech.thapelomalifi.recipes.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new AppUserDetails(userRepository.findByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User with username: " +username + " not found")
                )
        );
    }
}
