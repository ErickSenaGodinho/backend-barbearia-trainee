package com.ericksena.backendbarbeariatrainee.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ericksena.backendbarbeariatrainee.domain.model.User;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);
}
