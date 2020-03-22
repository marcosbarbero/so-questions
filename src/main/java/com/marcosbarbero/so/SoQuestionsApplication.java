package com.marcosbarbero.so;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SoQuestionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoQuestionsApplication.class, args);
    }


}
