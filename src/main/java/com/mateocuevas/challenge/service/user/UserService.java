package com.mateocuevas.challenge.service.user;

import com.mateocuevas.challenge.dto.UserDto;
import com.mateocuevas.challenge.entity.User;
import com.mateocuevas.challenge.enums.UserRole;

import java.util.Optional;

public interface UserService {
    UserDto getUser();
    User getUserAuthenticated();
    void save(User user);
    Optional<User>findById(Long id);
    Optional<User>findByRole(UserRole role);
    Optional<User> findByEmail(String email);
}
