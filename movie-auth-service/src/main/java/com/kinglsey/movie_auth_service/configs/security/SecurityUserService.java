package com.kinglsey.movie_auth_service.configs.security;

import com.kinglsey.movie_auth_service.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserService implements UserDetailsService {
    private final UserRepository userRepository;

    public SecurityUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findUsersByEmail(email);

        return user.map(SecurityUsers::new).orElseThrow(() -> new UsernameNotFoundException("Invalid credential"));
    }
}
