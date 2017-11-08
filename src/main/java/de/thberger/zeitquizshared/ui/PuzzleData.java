package de.thberger.zeitquizshared.ui;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author thb
 */
@RequiredArgsConstructor
@Getter
public class PuzzleData {

    @JsonProperty("puzzledata")
    private final List<Crossword> crosswordList;

    @Builder
    @Getter
    public static class Crossword {
        private final String clue;
        private final  String answer;
        private final  int position;
        private final  String orientation;
        private final  int startx;
        private final int starty;
    }
}
