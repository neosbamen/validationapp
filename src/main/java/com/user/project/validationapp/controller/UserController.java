package com.user.project.validationapp.controller;

import com.user.project.validationapp.model.User;
import com.user.project.validationapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> allUserAsList(){
        return userService.allUserAsList();
    }

    @PostMapping("/create")
    public void createUserC(@RequestBody User user){
        userService.createUser(user);
    }

    @PutMapping("/update/{id}")
    public void updateUserC(@PathVariable Long id, @RequestBody User user){

        userService.updateUser(id,user);
    }

    @DeleteMapping("/delete{id}")
    public void deleteUserC(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
