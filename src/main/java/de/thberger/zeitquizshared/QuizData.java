package de.thberger.zeitquizshared;

import lombok.Data;

import java.util.List;

/**
 * @author thb
 */
@Data
public class QuizData {

    private final Meta meta;

    private final List<Question> questions;

    @Data
    static class Meta {
        private final String key;
        private final String label;
        private final String date;
    }
}
