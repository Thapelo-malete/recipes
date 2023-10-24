package tech.thapelomalifi.recipes.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.thapelomalifi.recipes.exceptions.EmailTakenException;
import tech.thapelomalifi.recipes.model.AppUserDetails;
import tech.thapelomalifi.recipes.model.User;
import tech.thapelomalifi.recipes.model.UserRole;
import tech.thapelomalifi.recipes.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User registerUser(User user) {
        Optional<User> dbUser = userRepository.findByEmail(user.getEmail());

        if (dbUser.isPresent()) {
            throw new EmailTakenException("Email " +user.getEmail()+" is already taken");
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.USER);
        return userRepository.save(user);
    }

    public User getAuthenticatedUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        return userRepository.findByEmail(
                (
                        (AppUserDetails)authentication.getPrincipal()
                ).getUsername()
        ).orElseThrow();
    }
}
