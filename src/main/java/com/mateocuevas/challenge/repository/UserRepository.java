package com.mateocuevas.challenge.repository;

import com.mateocuevas.challenge.entity.User;
import com.mateocuevas.challenge.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    Optional<User> findByRole(UserRole role);
}
