package com.example.restfulwebservices.jpa;

import com.example.restfulwebservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
