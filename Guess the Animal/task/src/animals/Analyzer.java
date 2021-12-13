package animals;

import java.util.List;

public class Analyzer {
    public static boolean analyzeAnswer(String answer) throws Exception {
        List<String> positiveResponses = List.of(
                "y", "yes", "yeah", "yep", "sure", "right", "affirmative",
                "correct", "indeed", "you bet", "exactly", "you said it"
        );

        List<String> negativeResponses = List.of(
                "n", "no", "no way", "nah", "nope", "negative",
                "I don't think so", "yeah no"
        );

        if (anyMatch(answer, positiveResponses)) {
            return true;
        } else if (anyMatch(answer, negativeResponses)) {
            return false;
        } else {
            throw new Exception(RandomAnswers.generateMisperceptionAnswer());
        }
    }

    private static boolean anyMatch(String userAnswer, List<String> responses) {
        return responses.stream()
                .anyMatch(item -> item != null && userAnswer.trim().matches("(?i)" + item + "(!|\\.)?"));
    }
}
