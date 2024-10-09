package com.planitary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MDApplication {
    public static void main(String[] args) {
        SpringApplication.run(MDApplication.class, args);
    }
}
