package com.solance.banking.api;

import com.solance.banking.model.OpenAccount;
import com.solance.banking.model.UserTable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@ComponentScan
public interface UserAPI {

    public ResponseEntity<?> addUser(@RequestBody UserTable user);

    public ResponseEntity<?> createAccount(@RequestBody OpenAccount openAccount);

    public ResponseEntity<?> getUser(@RequestBody String userId);
}
