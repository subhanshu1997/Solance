package com.solance.banking.SQS;

import com.solance.banking.model.Message;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SqsException;
import software.amazon.awssdk.services.sqs.paginators.ListQueuesIterable;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class SQSQueue {

    public Queue<Message> queue;

    public SQSQueue() {
        this.queue = new LinkedList<Message>();
    }
}
