package com.example.ocm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ocm.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

	 Optional<User> findByEmail(String email);
}
