package com.rekrutacja.zadanie.repository;

import com.rekrutacja.zadanie.model.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPesel(String pesel);
}

