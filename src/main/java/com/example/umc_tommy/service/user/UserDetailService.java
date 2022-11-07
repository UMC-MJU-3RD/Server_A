package com.example.umc_tommy.service.user;

import com.example.umc_tommy.model.entity.User;
import com.example.umc_tommy.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(Long.parseLong(username));

        if (user == null) {
            throw new UsernameNotFoundException("404 - User Not Found");
        }

        return new UserDetails(user.get());
    }
}
