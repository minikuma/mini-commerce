package me.minikuma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MiniCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniCustomerApplication.class, args);
    }

}
