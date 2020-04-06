package com.savaari.savaariscraping;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScrapingApplication {
    private static final Logger log = LoggerFactory.getLogger(ScrapingApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ScrapingApplication.class, args);
    }
}
