package com.sheng.backendchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class BackendChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendChallengeApplication.class, args);
    }

}
