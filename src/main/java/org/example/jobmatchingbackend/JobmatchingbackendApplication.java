package org.example.jobmatchingbackend;

import org.example.jobmatchingbackend.userservice.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("org.example.jobmatchingbackend.userservice.model")
public class JobmatchingbackendApplication {

    public static void main(String[] args) {

        User user = new User();
        SpringApplication.run(JobmatchingbackendApplication.class, args);
    }

}
