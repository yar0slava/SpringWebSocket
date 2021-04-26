package com.java.server;

import com.java.server.config.DatabaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication
public class MyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyServerApplication.class, args);
    }

    @Autowired
    public void init() {
        DatabaseConfig.connect();
    }

    @PreDestroy
    public void close() {
        DatabaseConfig.close();
    }

}
