package com.example.personal_website.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    //SELECT * FROM users WHERE <column> = %<value>%
    @Query("SELECT u FROM Users u WHERE u.username = ?1")
    Optional<Users> findUsersByUsername(String username);
}
