package de.thberger.zeitquizshared;

import lombok.Data;

/**
 * @author thb
 */
@Data
public class Question {

    private final int key;
    private final Position position;

    private final Direction horizontal;

    private final Direction vertical;

    @Data
    private static class Position {
        private final int x;
        private final int y;
    }

    @Data
    private static class Direction {
        private final String question;
        private final String answer;
    }
}
