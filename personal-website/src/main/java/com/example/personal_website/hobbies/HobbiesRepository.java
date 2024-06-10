package com.example.personal_website.hobbies;

import com.example.personal_website.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HobbiesRepository extends JpaRepository<Hobbies, Long> {
    @Query("SELECT h FROM Hobbies  h WHERE h.name LIKE CONCAT('%', :name, '%')")
    Optional<Hobbies> findHobbiesByName(String name);

    //Search by user
    List<Hobbies> findHobbiesByUsers(Users user);
    //Search by User id
    List<Hobbies> findHobbiesByUserId(Long usersId);
}
