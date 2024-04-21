package com.user.backendspringbootapp.controllers;

import com.user.backendspringbootapp.entities.User;
import com.user.backendspringbootapp.exceptions.UserNotFoundException;
import com.user.backendspringbootapp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepo.save(newUser);
    }
    @GetMapping("/users")
    List<User> getAllUsers(){
        return  userRepo.findAll();
    }
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepo.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User UpdateUser(@RequestBody User newUser,@PathVariable Long id){
        return userRepo.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepo.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepo.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepo.deleteById(id);
        return "User by id "+id+" has been deleted successfully.";
    }
}
