package com.user.project.validationapp.controller;

import com.user.project.validationapp.model.User;
import com.user.project.validationapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> allUserAsList(){
        return userService.allUserAsList();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUserC(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUserC(@PathVariable Long id, @RequestBody User user){

        return userService.updateUser(id,user);
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<String> deleteUserC(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}
