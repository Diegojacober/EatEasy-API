package com.diegojacober.eateasyapi.rest.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegojacober.eateasyapi.domain.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    public Optional<User> findByEmail(String email);

}
