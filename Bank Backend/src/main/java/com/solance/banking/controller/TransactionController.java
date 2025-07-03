package com.solance.banking.controller;

import com.solance.banking.SQS.SQSQueue;
import com.solance.banking.api.TransactionAPI;
import com.solance.banking.model.DepositAmount;
import com.solance.banking.model.Error;
import com.solance.banking.model.OpenAccount;
import com.solance.banking.model.TransferInstruction;
import com.solance.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Queue;

@RestController
@RequestMapping("/api/v1")
public class TransactionController implements TransactionAPI {

    @Autowired
    TransactionService transactionService = new TransactionService();

    @Override
    @PutMapping("/deposit_amount")
    public ResponseEntity<?> depositAmount(@RequestBody DepositAmount depositAmount) {
        try {
            return transactionService.depositAmount(depositAmount);
        } catch (Exception e) {
            return new ResponseEntity<>(new Error(e.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("/transfer_amount")
    public ResponseEntity<?> transferAmount(@RequestBody TransferInstruction transferInstruction) {
        try {
            return transactionService.transferAmount(transferInstruction);
        } catch (Exception e) {
            return new ResponseEntity<>(new Error(e.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
