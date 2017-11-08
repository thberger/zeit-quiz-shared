package de.thberger.zeitquizshared.ui;

import de.thberger.zeitquizshared.ui.PuzzleData;
import de.thberger.zeitquizshared.zeitde.Question;
import de.thberger.zeitquizshared.zeitde.QuizData;
import de.thberger.zeitquizshared.zeitde.QuizFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author thb
 */
@RestController
@RequiredArgsConstructor
public class QuizDataController {

    private final QuizFetcher quizFetcher;

    @GetMapping(path = "/quizdata")
    public PuzzleData quizData() {
        QuizData quizData = quizFetcher.getQuizData();
        List<PuzzleData.Crossword> collect = quizData.getQuestions().stream()
                .map(this::toCrossWord).collect(Collectors.toList());
        return new PuzzleData(collect);
    }

    private PuzzleData.Crossword toCrossWord(Question q) {
        return PuzzleData.Crossword.builder()
                .answer(q.getQuestion().getAnswer())
                .clue(q.getQuestion().getQuestion())
                .orientation(mapOrientation(q.getOrientation()))
                .startx(q.getPosition().getX())
                .starty(q.getPosition().getY())
                .position(q.getKey()).build();
    }

    private String mapOrientation(Question.Orientation o) {
        switch (o) {
            case HORIZONTAL: return "accross";
            case VERTICAL: return "down";
        }
        throw new IllegalStateException("Missing data");
    }

}
