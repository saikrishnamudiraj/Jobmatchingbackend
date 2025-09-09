package org.example.jobmatch.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("org.example.jobmatch.user.entity")
public class userServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(userServiceApplication.class, args);
    }
}
