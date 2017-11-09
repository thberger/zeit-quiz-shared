package de.thberger.zeitquizshared.zeitde;

import lombok.Data;

/**
 * @author thb
 */
@Data
public class Question {

    public enum Orientation {
        VERTICAL,
        HORIZONTAL
    }

    private final int key;
    private final Position position;

    private final Direction horizontal;

    private final Direction vertical;

    public Direction getQuestion() {
        switch (getOrientation()) {
            case HORIZONTAL: return horizontal;
            case VERTICAL: return vertical;
            default: throw new IllegalStateException("Missing question data for key " + key);
        }
    }

    public Orientation getOrientation() {
        if (horizontal != null) {
            return Orientation.HORIZONTAL;
        } else if (vertical != null){
            return Orientation.VERTICAL;
        } else {
            throw new IllegalStateException("Missing question data for key " + key);
        }
    }

    @Data
    public static class Position {
        private final int x;
        private final int y;
    }

    @Data
    public static class Direction {
        private final String question;
        private final String answer;
    }
}
