package com.example.personal_website.users;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getUsers(){
        //THERE ARE DIFF SORTING METHODS KEEP IN MIND
        return usersRepository.findAll();
    }

    public void addNewUsers(Users users) {
        Optional<Users> usersOptional = usersRepository.findUsersByUsername(users.getUsername());

        if (usersOptional.isPresent()){
            throw new IllegalStateException("Username Taken.");
        }
       usersRepository.save(users);
    }

    //DELETE users by id
    public void deleteUsers(Long usersId) {
       boolean exists = usersRepository.existsById(usersId);
       if(!exists){
           throw new IllegalStateException("User ID: " + usersId + " does not exist.");
       }
       usersRepository.deleteById(usersId);
    }
    @Transactional
    public void updateUsers(Long usersId,
                            String username,
                            String name,
                            LocalDate dob) {
        Users users = usersRepository.findById(usersId).orElseThrow(()
                -> new IllegalStateException("User ID: " + usersId + " does not exist."));

        //Update recorded user username entry
        if (username != null && !username.isEmpty() && !Objects.equals(users.getUsername(), username)){
            Optional<Users> usersOptional = usersRepository.findUsersByUsername(username);
            if(usersOptional.isPresent()){
                throw new IllegalStateException("Username " + username + " is taken.");
            }
            users.setUsername(username);
        }

        //Update recorded name entry
        if(name != null && !name.isEmpty() && !Objects.equals(users.getName(), name)){
            users.setName(name);
        }
        users.setDob(dob);
    }
}
