package com.solance.banking.service;

import com.solance.banking.SQS.SQSQueue;
import com.solance.banking.dao.UserRepository;
import com.solance.banking.model.*;
import com.solance.banking.model.Error;
import com.solance.banking.model.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SQSQueue sqsQueue;

    //Register new User
    public ResponseEntity<?> addUser( UserTable user) {
        try {
            if(userRepository.findById(user.getUserId()).isEmpty()) {
                userRepository.save(user);
                Message message = new Message("User Added","User added with user id: " + user.getUserId());
                sqsQueue.queue.add(message);
                return new ResponseEntity<>(user, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("User Already Exist", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Error(e.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Open account of user
    public ResponseEntity<?> createAccount(OpenAccount openAccount) {
        try {
            Optional<UserTable> optional = userRepository.findById(openAccount.getUserId());
            if(optional.isPresent() && optional.get().getBalance() == null) {
                UserTable u = optional.get();
                u.setBalance(openAccount.getBalance());
                userRepository.save(u);
                Message message = new Message("Account Created","Account created for user id : " + openAccount.getUserId());
                sqsQueue.queue.add(message);

                return new ResponseEntity<>(u, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("User Doesn't Exist", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Error(e.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get user account
    public ResponseEntity<?> getUser(String userId) {
        try {
            Optional<UserTable> optional = userRepository.findById(userId);
            if(optional.isPresent()) {
                UserTable u = optional.get();
                return new ResponseEntity<>(u, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("User Doesn't Exist", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Error(e.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
