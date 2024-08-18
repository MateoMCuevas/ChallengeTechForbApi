package com.mateocuevas.challenge.service.user;

import com.mateocuevas.challenge.entity.User;
import com.mateocuevas.challenge.enums.UserRole;
import com.mateocuevas.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //Find user authenticated in  the moment
        String username = authentication.getName();
        return findByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByRole(UserRole role) {return userRepository.findByRole(role);
    }

    @Override
    public Optional<User> findByUsername(String username) {return userRepository.findByEmail(username);
    }
}
