package animals;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class RandomAnswers {
    public static String greet() {
        LocalTime currentTime = LocalTime.now();
        LocalTime morningStart = LocalTime.of(5, 0);
        LocalTime afternoonStart = LocalTime.of(12, 0);
        LocalTime eveningStart = LocalTime.of(18, 0);

        String greeting = "Hello!";
        if (
                currentTime.compareTo(morningStart) >= 0 &&
                        currentTime.compareTo(afternoonStart) < 0
        ) {
            greeting = "Good morning";
        } else if (
                currentTime.compareTo(afternoonStart) >= 0 &&
                        currentTime.compareTo(eveningStart) < 0
        ) {
            greeting = "Good afternoon";
        } else if (currentTime.compareTo(eveningStart) >= 0) {
            greeting = "Good evening";
        }

        return greeting;
    }

    public static String tellGoodbye() {
        List<String> goodbyes = List.of(
                "Goodbye", "Bye", "See you next time", "Later"
        );
        Random random = new Random();
        return goodbyes.get(random.nextInt(goodbyes.size()));
    }

    public static String generateMisperceptionAnswer() {
        List<String> misperceptionResponses = List.of(
                "I'm not sure I caught you: was it yes or no?",
                "Funny, I still don't understand, is it yes or no?",
                "Oh, it's too complicated for me: just tell me yes or no.",
                "Could you please simply say yes or no?",
                "Oh, no, don't try to confuse me: say yes or no."
        );

        Random random = new Random();
        String programPerceptionAnswer = misperceptionResponses.get(
                random.nextInt(misperceptionResponses.size())
        );

        return programPerceptionAnswer;
    }
}
