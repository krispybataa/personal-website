package com.example.personal_website.todos;

import com.example.personal_website.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodosRepository extends JpaRepository<Todos, Long> {
    //Find by Category
    @Query("SELECT t FROM Todos t WHERE t.category = ?1")
    Optional<Todos> findTodosByCategory(String category);
    //Find task
    @Query("SELECT t FROM Todos t WHERE t.task LIKE CONCAT('%', :task, '%')")
    Optional<Todos> findTodosByTask(String task);

    //Find User
    List<Todos> findTodosByUsers(Users users);
    //Find by user ID
//    List<Todos> findTodosByUsersId(Long usersId);

}
