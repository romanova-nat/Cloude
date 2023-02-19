package com.example.clouddiplom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class})
public class CloudDiplomApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudDiplomApplication.class, args);
    }


}
