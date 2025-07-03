package com.solance.banking.controller;

import com.solance.banking.SQS.SQSQueue;
import com.solance.banking.api.QueueAPI;
import com.solance.banking.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class QueueController implements QueueAPI {

    @Autowired
    SQSQueue sqsQueue;

    @Override
    @GetMapping("/poll")
    public Queue<Message> pollQueue() {
        return sqsQueue.queue;
    }
}
