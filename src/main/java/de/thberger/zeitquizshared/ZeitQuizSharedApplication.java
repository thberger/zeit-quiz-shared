package de.thberger.zeitquizshared;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Slf4j
public class ZeitQuizSharedApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZeitQuizSharedApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(QuizFetcher quizFetcher) throws Exception {
		return args -> {
			QuizData quizData = quizFetcher.fetch();
			String label = quizData.getMeta().getLabel();
			String date = quizData.getMeta().getDate();
			log.info("Fetched '{}' from {} with {} questions", label, date, quizData.getQuestions().size());
		};
	}


}
