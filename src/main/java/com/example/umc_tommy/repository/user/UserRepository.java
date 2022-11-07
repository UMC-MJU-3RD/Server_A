package com.example.umc_tommy.repository.user;

import com.example.umc_tommy.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
