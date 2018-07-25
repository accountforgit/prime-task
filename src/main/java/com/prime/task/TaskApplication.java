package com.prime.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prime.task.services.RemoteService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static com.prime.task.TaskApplication.HEADERS;

@SpringBootApplication
@EnableScheduling
public class TaskApplication {


	public static String[] HEADERS = { "caseId", "insertDate","startDate",
			"endDate","latitude","longitude","sourceName","info"};

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}

	@Bean
	public RestTemplate getRest(){
		return new RestTemplate();
	}
	@Bean
	ObjectMapper getMapper(){
		return new ObjectMapper();
	}
}

@Component
class Integrate{

	private static Logger log = LoggerFactory.getLogger(Integrate.class);
	private Environment env;
	private RemoteService fileService;
	private RemoteService apiService;
	int delay=0;
	int period=0;
	@Autowired
	public Integrate(Environment environment,@Qualifier("fileService") RemoteService fs,
					 @Qualifier("apiService") RemoteService as){
		env=environment;
		fileService=fs;
		apiService=as;
		period= Integer.parseInt(env.getProperty("period"));
	}

	@Scheduled(fixedDelay = 30000)
	public void integrate() throws IOException {
		if(period==60000 && delay%2==1){
			delay++;
			return;
		}
		FileWriter out = new FileWriter("destination_table.csv");
		try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
				.withHeader(HEADERS))) {
			List<DataModel> apiServiceData = apiService.getData();
			List<DataModel> fileServiceData = fileService.getData();
			log.info("procedure started");
			Stream.concat(fileServiceData.stream(),apiServiceData.stream()).forEach(w->{
				try {
					printer.printRecord(w.getCaseId(),w.getInsertDate(),w.getStartDate()
					,w.getEndDate(),w.getLatitude(),w.getLongitude(),w.getSourceName(),w.getInfo());
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			log.info("procedure ended");
		}

	}

}
