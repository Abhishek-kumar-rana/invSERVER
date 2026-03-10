package com.invoice.backd.repository;

import com.invoice.backd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByClerkId(String clerkId);
    boolean existsByClerkId(String clerkId);
}
