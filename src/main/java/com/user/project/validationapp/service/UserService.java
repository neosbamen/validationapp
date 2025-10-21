package com.user.project.validationapp.service;

import com.user.project.validationapp.model.User;
import com.user.project.validationapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> allUserAsList(){

        return userRepository.findAll();
    }
    public void createUser(User user){

        boolean exist=allUserAsList().stream().anyMatch(e->e.getEmail().equals(user.getEmail()));

        if (exist){
            System.out.println("Email account used already");
        }else {
            userRepository.save(user);
            System.out.println("User created");
        }
    }
}
