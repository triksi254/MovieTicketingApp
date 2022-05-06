package com.example.ticketing.repository;

import com.example.ticketing.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository <Users, Long> {
//    @Query("SELECT u FROM Users u join u.roles r WHERE u.username = :username and r.roleName='USER'")
//    List<Users> findUser(@Param("username") String username);

   Optional<Users> findByEmail(String email);
}

