package com.solance.banking.controller;

import com.solance.banking.api.UserAPI;
import com.solance.banking.model.Error;
import com.solance.banking.model.OpenAccount;
import com.solance.banking.model.UserTable;
import com.solance.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController implements UserAPI {

    @Autowired
    UserService userService = new UserService();

    @Override
    @PostMapping("/add_user")
    public ResponseEntity<?> addUser(@RequestBody UserTable user) {
        try {
            return userService.addUser(user);
        } catch (Exception e) {
            return new ResponseEntity<>(new Error(e.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("/create_account")
    public ResponseEntity<?> createAccount(@RequestBody OpenAccount openAccount) {
        try {
            return userService.createAccount(openAccount);
        } catch (Exception e) {
            return new ResponseEntity<>(new Error(e.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/get_user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        try {
            return userService.getUser(userId);
        } catch (Exception e) {
            return new ResponseEntity<>(new Error(e.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}