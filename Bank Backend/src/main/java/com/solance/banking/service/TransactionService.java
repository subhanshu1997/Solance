package com.solance.banking.service;

import com.solance.banking.SQS.SQSQueue;
import com.solance.banking.dao.UserRepository;
import com.solance.banking.model.*;
import com.solance.banking.model.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SQSQueue sqsQueue;

    //Deposit Money into existing bank account
    public ResponseEntity<?> depositAmount(DepositAmount depositAmount) {
        try {
            Optional<UserTable> optional = userRepository.findById(depositAmount.getUserId());
            if(optional.isPresent()) {
                UserTable user = optional.get();
                user.setBalance(user.getBalance() + depositAmount.getAmount());
                userRepository.save(user);
                Message message = new Message("Amount ", depositAmount.getUserId() + " deposited in account " + depositAmount.getUserId());
                sqsQueue.queue.add(message);

                return new ResponseEntity<>(depositAmount, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("User Doesn't Exist", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(new Error(e.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Transfer money from one account to another
    public ResponseEntity<?> transferAmount(TransferInstruction transferInstruction) {
        try {
            Optional<UserTable> optional = userRepository.findById(transferInstruction.getUserId());
            if(optional.isPresent()) {
                UserTable user = optional.get();
                if(user.getBalance() < transferInstruction.getAmountSell()){
                    return new ResponseEntity<>("Insufficient Balance", HttpStatus.INTERNAL_SERVER_ERROR);
                }else if(user.getIban().equals(transferInstruction.getBeneficiaryIban())){
                    return new ResponseEntity<>("Depositor and Beneficiary account cannot be same", HttpStatus.INTERNAL_SERVER_ERROR);
                }
                else{
                    Double actualAmount = transferInstruction.getAmountSell() * transferInstruction.getRate();
                    Iterator<UserTable> userList = userRepository.findAll().iterator();
                    while(userList.hasNext()){
                        UserTable nextUser = userList.next();
                        if(nextUser.getIban().equals(transferInstruction.getBeneficiaryIban())){
                            user.setBalance(user.getBalance() - transferInstruction.getAmountSell());
                            nextUser.setBalance(nextUser.getBalance() + actualAmount);
                            userRepository.save(nextUser);
                            userRepository.save(user);
                            Message message = new Message("Transfer Amount","Amount " + transferInstruction.getAmountSell() + " transferred to " + transferInstruction.getBeneficiaryIban());
                            sqsQueue.queue.add(message);
                            return new ResponseEntity<>("Transaction Successfull", HttpStatus.OK);
                        }
                    }
                }
                return new ResponseEntity<>("Beneficiary user does not exist", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>("User does not exist", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(new Error(e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
