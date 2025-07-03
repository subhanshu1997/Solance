package com.solance.banking.api;

import com.solance.banking.model.DepositAmount;
import com.solance.banking.model.TransferInstruction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface TransactionAPI {

    public ResponseEntity<?> depositAmount(@RequestBody DepositAmount depositAmount);

    public ResponseEntity<?> transferAmount(@RequestBody TransferInstruction transferInstruction);
}
