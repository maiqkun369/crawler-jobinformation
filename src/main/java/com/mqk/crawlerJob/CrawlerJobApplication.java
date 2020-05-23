package com.mqk.crawlerJob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CrawlerJobApplication {
	public static void main(String[] args) {
		SpringApplication.run(CrawlerJobApplication.class,args);
	}
}
