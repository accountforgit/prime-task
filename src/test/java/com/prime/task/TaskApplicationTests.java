package com.prime.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskApplicationTests {
	@Value("${task.baseUrl}")
	private String baseUrl;

	@Test
	public void contextLoads() {
		assertEquals(baseUrl,"http://api.openweathermap.org/data/2.5/forecast?q=Almaty,kaz&APPID=03b00a484b0b5477138cd317fed33835&units=metric");
	}

}
