package com.mqk.crawlerJob.task.test;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskTest {
	//@Scheduled(cron = "0/5 * * * * *")
	public void tet(){
		System.out.println("(灬ꈍ ꈍ灬)");
	}
}
