package com.example.personal_website.todos;

import com.example.personal_website.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodosService {
    private final TodosRepository todosRepository;
    @Autowired
    public TodosService(TodosRepository todosRepository){this.todosRepository = todosRepository;}
    public List<Todos> getAllTodos() {
        //NOTE DIFF SORTING METHODS
        return todosRepository.findAll();
    }

    public void addNewTodos(Todos todos) {
        Optional<Todos> todosOptional = todosRepository.findTodosByTask(todos.getTask());
        if(todosOptional.isPresent()){
            throw new IllegalStateException("Task already exists.");
        }
        todosRepository.save(todos);
    }

    public void updateTodos(Long todosId, String category, String task, String description) {
        Todos todos = todosRepository.findById(todosId).orElseThrow(()
                -> new IllegalStateException("Task ID: " + todosId + " does not exist."));

        //Update recorded task category
        if (category != null && !category.isEmpty()) {
            todos.setCategory(category);
        }

        if (task != null && !task.isEmpty()) {
            Optional<Todos> todosOptional = todosRepository.findTodosByTask(task);
            if (todosOptional.isPresent()) {
                throw new IllegalStateException("Task already exists.");
            }
            todos.setTask(task);
        }

        if (description != null && !description.isEmpty()) {
            todos.setDescription(description);
        }

        todosRepository.save(todos);
    }

    public void deleteTodos(Long todosId) {
        boolean exists = todosRepository.existsById(todosId);
        if (!exists) {
            throw new IllegalStateException("To-do with id " + todosId + " does not exist.");
        }
        todosRepository.deleteById(todosId);
    }

    public Optional<Todos> getTodosByTask(String task) {return todosRepository.findTodosByTask(task);}

    public Optional<Todos> getTodosByCategory(String category) {return todosRepository.findTodosByCategory(category);}

    public List<Todos> getTodosByUser(Users users) {return todosRepository.findTodosByUsers(users);}

    public void markTodoAsCompleted(Long todosId, boolean completed) {
        Todos todos = todosRepository.findById(todosId).orElseThrow(() ->
                new IllegalStateException("Task ID: " + todosId + " does not exist."));
        todos.setCompleted(completed);
        todosRepository.save(todos);
    }

//    public List<Todos> getTodosByUserId(Long usersId) {return todosRepository.findTodosByUsersId(usersId);}
}
