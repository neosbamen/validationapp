package com.user.project.validationapp.service;

import com.user.project.validationapp.model.User;
import com.user.project.validationapp.model.UserDTO;
import com.user.project.validationapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {


    private final UserRepository userRepository;

    public ResponseEntity<Boolean> findUserByEmailAndPassword(User user){
       User userExist=userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());

       if (userExist!=null){

           return new ResponseEntity<>(true,HttpStatus.OK);
       }

        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<List<UserDTO>> allUserAsList(){

        List<UserDTO> userDTOList = new ArrayList<>();

        userRepository.findAll().forEach(user -> {

            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getUserId());
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTOList.add(userDTO);

        });

        return new ResponseEntity<>(userDTOList,HttpStatus.OK);
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
