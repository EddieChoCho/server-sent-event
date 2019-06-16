package com.eddie.serversentevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ServerSentEventApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerSentEventApplication.class, args);
    }

}
