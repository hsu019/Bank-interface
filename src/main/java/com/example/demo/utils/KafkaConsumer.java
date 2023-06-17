package com.example.demo.utils;

import com.example.demo.mapper.TransactionMapper;
import com.example.demo.models.Transaction;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.util.Optional;


@Component
public class KafkaConsumer {
    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private TransactionMapper transactionMapper;

//    @KafkaListener(topics = {"transaction"})
//    public void onMessage1(ConsumerRecord<?, ?> consumerRecord) {
//        Optional<?> optional = Optional.ofNullable(consumerRecord.value());
//        if (optional.isPresent()) {
//            Transaction msg = (Transaction)optional.get();
//            transactionMapper.insert(msg);
//            logger.info("message:{}", msg);
//        }
//    }
}
