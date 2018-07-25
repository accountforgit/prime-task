package com.prime.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableScheduling
public class TaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}
}

@Component
class Integrate{

	private static Logger log = LoggerFactory.getLogger(Integrate.class);

	Environment env;



	@Autowired
	public Integrate(Environment environment){
		env=environment;
	}

	@Scheduled(fixedDelay = 30000)
	public void integrate(){

	}

}
