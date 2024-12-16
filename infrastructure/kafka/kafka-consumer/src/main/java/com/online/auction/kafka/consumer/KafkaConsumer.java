package com.online.auction.kafka.consumer;

import java.util.List;

public interface KafkaConsumer {
    void receive(List<String> messages, List<Long> keys, List<Integer> partitions, List<Long> offsets);
}
