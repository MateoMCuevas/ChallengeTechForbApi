package com.mateocuevas.challenge.service.user;

import com.mateocuevas.challenge.dto.UserDto;
import com.mateocuevas.challenge.entity.User;
import com.mateocuevas.challenge.enums.UserRole;
import com.mateocuevas.challenge.exception.UserNotFoundException;
import com.mateocuevas.challenge.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    public UserDto getUser()
    {
        User user=getUserAuthenticated();
        return UserDto.builder()
                .name(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public User getUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof User) {
                return (User) principal;
            }else{
            throw new UserNotFoundException("there is no authenticated user");
            }
        }
        return null;
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
    public Optional<User> findByEmail(String email) {return userRepository.findByEmail(email);
    }
}
