package com.user.project.validationapp.service;

import com.user.project.validationapp.model.User;
import com.user.project.validationapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<List<User>> allUserAsList(){

        return new ResponseEntity<>(userRepository.findAll(),HttpStatus.OK);
    }
    public ResponseEntity<String> createUser(User user){

        boolean exist= userRepository.findAll().stream().anyMatch(e->e.getEmail().equals(user.getEmail()));

        if (exist){
            return new ResponseEntity<>("Email account used already",HttpStatus.NOT_FOUND);
        }else {
            userRepository.save(user);
           return new ResponseEntity<>("User created",HttpStatus.CREATED);
        }
    }
    public ResponseEntity<String> updateUser(Long id, User user){

        Optional<User> exist=userRepository.findById(id);

        if (exist.isPresent()){
            exist.get().setName(user.getName());
            exist.get().setEmail(user.getEmail());
            exist.get().setPassword(user.getPassword());
            userRepository.save(exist.get());
            return new ResponseEntity<>("User set successfully",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Not user match the Id provided",HttpStatus.CONFLICT);
        }


    }
    public ResponseEntity<String> deleteUser(Long id){
        Optional<User> exist=userRepository.findById(id);
        if (exist.isPresent()){
            userRepository.delete(exist.get());
            return new ResponseEntity<>("User found and delete",HttpStatus.OK);
        }else {
            return  new ResponseEntity<>("Not user match the id provided",HttpStatus.CONFLICT);
        }
    }

}
