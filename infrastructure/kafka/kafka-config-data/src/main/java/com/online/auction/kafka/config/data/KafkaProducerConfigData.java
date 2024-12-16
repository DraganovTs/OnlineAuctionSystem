package com.online.auction.kafka.config.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka-producer-config")
public class KafkaProducerConfigData {
    private String keySerializerClass;
    private String valueSerializerClass;
    private String acks;
    private Integer lingerMs;
    private Integer deliveryTimeoutMs;
    private Integer requestTimeoutMs;
    private Integer retryCount;
    private Integer maxInFlightRequestPerConnection;
    private Boolean idempotence;
}
