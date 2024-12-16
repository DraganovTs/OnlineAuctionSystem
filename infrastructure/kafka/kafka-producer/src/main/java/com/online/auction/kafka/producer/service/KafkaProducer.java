package com.online.auction.kafka.producer.service;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

public interface KafkaProducer {

    void send(String topicName, String message, CompletableFuture<SendResult<String, Object>> callback);
}
