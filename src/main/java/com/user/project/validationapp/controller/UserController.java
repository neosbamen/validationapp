package com.user.project.validationapp.controller;

import com.user.project.validationapp.model.User;
import com.user.project.validationapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> allUserAsList(){
        return userService.allUserAsList();
    }

    @PostMapping("/create")
    public void createUserC(@RequestBody User user){
        userService.createUser(user);
    }
}
