package de.thberger.zeitquizshared.zeitde;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author thb
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class QuizFetcher {

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;
    private static final String URL = "http://spiele.zeit.de/ecke/ecke_json.php";
    private QuizData quizData;

    @PostConstruct
    public QuizData fetch() {
        log.debug("Fetching quiz from " + URL);
        HttpHeaders headers = createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
        return parse(response.getBody());
    }

    private QuizData parse(String body) {
        try {
            return objectMapper.readValue(body, QuizData.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void logInfo(QuizData quizData) {
        String label = quizData.getMeta().getLabel();
        String date = quizData.getMeta().getDate();
        log.info("Fetched '{}' from {} with {} questions", label, date, quizData.getQuestions().size());
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "text/javascript, application/javascript, */*");
        headers.add(HttpHeaders.ACCEPT_LANGUAGE, "de,en-US;q=0.7,en;q=0.3");
        headers.add(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
        headers.add(HttpHeaders.REFERER, "http://spiele.zeit.de/ecke/html5/index.php");
        headers.add("X-Requested-With", "XMLHttpRequest" );
        headers.add(HttpHeaders.CONNECTION, "keep-alive" );
        return headers;
    }

    public QuizData getQuizData() {
        if (quizData == null) {
            quizData = fetch();
            logInfo(quizData);
        }
        return quizData;
    }
}
