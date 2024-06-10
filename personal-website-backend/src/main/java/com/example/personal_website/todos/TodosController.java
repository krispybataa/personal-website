package com.example.personal_website.todos;

import com.example.personal_website.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/todos")
public class TodosController {
    private final TodosService todosService;
    @Autowired
    public TodosController(TodosService todosService){this.todosService = todosService;}
    @GetMapping
    public List<Todos> getAllTodos(){return todosService.getAllTodos();}
    @GetMapping(path = "/task/{task}")
    public Optional<Todos> getTodosByTask(@RequestBody String task){
        return todosService.getTodosByTask(task);
    }
    @GetMapping(path = "/category/{category}")
    public Optional<Todos> getTodosByCategory(@RequestBody String category){return todosService.getTodosByCategory(category);}
//    @GetMapping(path = "/users/{usersId}")
//    public List<Todos> getTodosByUserId(@PathVariable Long usersId){return todosService.getTodosByUserId(usersId);}
    @GetMapping(path = "/users/{users}")
    public List<Todos> getTodosByUser(@PathVariable Users users){return todosService.getTodosByUser(users);}
    @PostMapping
    public void registerTodos(@RequestBody Todos todos){todosService.addNewTodos(todos);}
    @PutMapping(path = "/{todosId}/complete")
    public void markTodoAsCompleted(@PathVariable Long todosId, @RequestParam boolean completed) {
        todosService.markTodoAsCompleted(todosId, completed);
    }
    @PutMapping
    public void updateTodos(
            @PathVariable("todosId") Long todosId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String task,
            @RequestParam(required = false) String description){
        todosService.updateTodos(todosId, category, task, description);
    }
    @DeleteMapping(path = "{todosId}")
    public void deleteTodos(@PathVariable("todosId") Long todosId) {todosService.deleteTodos(todosId);}
}
