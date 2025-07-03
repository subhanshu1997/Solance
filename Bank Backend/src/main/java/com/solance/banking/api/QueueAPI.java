package com.solance.banking.api;

import com.solance.banking.model.Message;

import java.util.Queue;

public interface QueueAPI {

    public Queue<Message> pollQueue();
}
