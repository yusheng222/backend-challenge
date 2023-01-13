package com.sheng.backendchallenge.service.impl;


import com.alibaba.fastjson.JSON;
import com.sheng.backendchallenge.config.Consumer;
import com.sheng.backendchallenge.dto.KafkaTransactionDto;
import com.sheng.backendchallenge.entity.Transaction;
import com.sheng.backendchallenge.service.ConsumerService;
import com.sheng.backendchallenge.service.TransactionService;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    TransactionService transactionService;

    @Value("${spring.kafka.bootstrap-servers}")
    private String server;

    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private boolean enableAutoCommit;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Value("${spring.kafka.consumer.key-deserializer}")
    private String keyDeserializer;

    @Value("${spring.kafka.consumer.value-deserializer}")
    private String valueDeserializer;

    @Value("${spring.kafka.consumer.max-poll-records}")
    private String maxPollRecords;

    @Value("${kafka.transaction.topic}")
    private String topic;

    @Override
    @Transactional
    public void consumerByTime(String groupId, long beginTime, long endTime,List<Long> accNums,Long cusId) {

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerConfigs(groupId));

        try {
            boolean isBreak = false;
            List<PartitionInfo> partitionInfos = consumer.partitionsFor(topic);
            List<TopicPartition> topicPartitions = new ArrayList<>();
            Map<TopicPartition,Long> timestampsToSearch = new HashMap<>();
            long fetchDataTime = beginTime;
            for (PartitionInfo partitionInfo : partitionInfos) {
                topicPartitions.add(new TopicPartition(partitionInfo.topic(),partitionInfo.partition()));
                timestampsToSearch.put(new TopicPartition(partitionInfo.topic(),partitionInfo.partition()),Long.valueOf(fetchDataTime));
            }
            // assign partitions of topic
            consumer.assign(topicPartitions);

            // set up timestamp
            Map<TopicPartition, OffsetAndTimestamp> map = consumer.offsetsForTimes(timestampsToSearch);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            OffsetAndTimestamp offsetAndTimestamp = null;

            // set up initial offset for each partitions
            Iterator<Map.Entry<TopicPartition, OffsetAndTimestamp>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<TopicPartition, OffsetAndTimestamp> next = iterator.next();
                offsetAndTimestamp = next.getValue();
                if (offsetAndTimestamp != null) {
                    int partition = next.getKey().partition();
                    long timestamp = offsetAndTimestamp.timestamp();
                    long offset = offsetAndTimestamp.offset();
                    consumer.seek(next.getKey(), offset);
                }
            }
            List<Transaction> transactionList = new ArrayList<>();
            do {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                if (records.isEmpty()){
                    isBreak = true;
                    break;
                }
                System.out.println("================拉取中：" + records.count()+"================");
                for (ConsumerRecord<String, String> record : records) {
                    KafkaTransactionDto kafkaTransactionDto = JSON.parseObject(record.value(), KafkaTransactionDto.class);
                    if (record.timestamp() >= endTime) {
                        isBreak = true;
                        break;
                    }
                    // check if the transaction belongs to the customer
                    if (!checkAccounts(record.key(),accNums)){
                        continue;
                    }
                    // put the data into database so that can return page list
                    Transaction transaction = new Transaction();
                    transaction.setTransactionId(kafkaTransactionDto.getUniIdentifier());
                    transaction.setAccId(Long.valueOf(record.key()));
                    String amountCur = kafkaTransactionDto.getAmountCur();
                    String[] split = amountCur.split(" ");
                    String currency = split[0];
                    BigDecimal amount = new BigDecimal(split[1]);
                    transaction.setCurrency(currency);
                    transaction.setAmount(amount);
                    transaction.setAccountIban(kafkaTransactionDto.getAccountIban());
                    transaction.setDescription(kafkaTransactionDto.getDescription());
                    transaction.setValueDate(kafkaTransactionDto.getValueDate());
                    transaction.setCusId(cusId);
                    transactionList.add(transaction);
                }
                transactionService.saveBatch(transactionList);
            }while (!isBreak);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            consumer.close();
        }
    }

    private boolean checkAccounts(String key,List<Long> accNums) {
        Long keyAcc = Long.valueOf(key);
        for (Long accNum : accNums) {
            if (keyAcc.equals(accNum)){
                return true;
            }
        }
        return false;
    }

    public Map<String, Object> consumerConfigs(String groupId){
        Map<String,Object> propsMap = new HashMap<>();
        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,server);
        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,enableAutoCommit);
        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,autoOffsetReset);
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,keyDeserializer);
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,valueDeserializer);
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        propsMap.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG,1000);
        propsMap.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,maxPollRecords);
        return propsMap;
    }
}
