package com.example.personal_website.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService){
        this.usersService = usersService;
    }
    @GetMapping
    public List<Users> getUsers(){
        return usersService.getUsers();
    }
    @PostMapping
    public void registerUser(@RequestBody Users users){
        usersService.addNewUsers(users);
    }

    @PutMapping
    public void updateUser(
            @PathVariable("usersId") Long usersId,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String name,
            @RequestParam(required = false)LocalDate dob){
        usersService.updateUsers(usersId,username,name,dob);
    }
    @DeleteMapping(path = "{usersId}")
    public void deleteUser(@PathVariable("usersId") Long usersId){
        usersService.deleteUsers(usersId);
    }
}
