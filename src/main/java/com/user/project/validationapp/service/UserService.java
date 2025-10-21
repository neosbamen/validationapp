package com.user.project.validationapp.service;

import com.user.project.validationapp.model.User;
import com.user.project.validationapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
    public void updateUser(Long id, User user){

        Optional<User> exist=userRepository.findById(id);

        if (exist.isPresent()){
            exist.get().setName(user.getName());
            exist.get().setEmail(user.getEmail());
            exist.get().setPassword(user.getPassword());
            userRepository.save(exist.get());
        }else {
            System.out.println("Not user match de Id provided");
        }


    }
    public void deleteUser(Long id){
        Optional<User> exist=userRepository.findById(id);
        if (exist.isPresent()){
            userRepository.delete(exist.get());
        }else {
            System.out.println("Not user match the id provided");
        }
    }

}
