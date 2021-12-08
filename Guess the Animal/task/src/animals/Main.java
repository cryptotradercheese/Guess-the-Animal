package animals;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(greet());
        System.out.println();

        System.out.println("Enter an animal:");
        String animal = scanner.nextLine();
        System.out.println("Is it " + formatAnimalName(animal) + "?");


        while (true) {
            String userAnswer = scanner.nextLine();

            try {
                System.out.println(analyzeAnswer(userAnswer));
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println();
        System.out.println(tellGoodbye());
    }

    public static String greet() {
        LocalTime currentTime = LocalTime.now();
        LocalTime morningStart = LocalTime.of(5, 0);
        LocalTime afternoonStart = LocalTime.of(12, 0);
        LocalTime eveningStart = LocalTime.of(18, 0);

        String greeting = "";
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

    public static String formatAnimalName(String name) {
        String animalNamePattern = "[a-z]+(\\s[a-z]+)*";

        if (name.matches("(?i)an?\\s" + animalNamePattern)) {
            return name.toLowerCase();
        } else if (
                name.matches("(?i)the\\s" + animalNamePattern) ||
                name.matches("(?i)" + animalNamePattern)
        ) {
            String nameWithoutArticle = name.replaceFirst("(?i)the\\s", "");
            String article = nameWithoutArticle.matches("(?i)[aeio].*") ? "an" : "a";

            return article + " " + nameWithoutArticle.toLowerCase();
        } else {
            throw new IllegalArgumentException("Wrong name format");
        }
    }

    public static String analyzeAnswer(String answer) throws Exception {
        List<String> positiveResponses = List.of(
                "y", "yes", "yeah", "yep", "sure", "right", "affirmative",
                "correct", "indeed", "you bet", "exactly", "you said it"
        );

        List<String> negativeResponses = List.of(
                "n", "no", "no way", "nah", "nope", "negative",
                "I don't think so", "yeah no"
        );

        List<String> misperceptionResponses = List.of(
                "I'm not sure I caught you: was it yes or no?",
                "Funny, I still don't understand, is it yes or no?",
                "Oh, it's too complicated for me: just tell me yes or no.",
                "Could you please simply say yes or no?",
                "Oh, no, don't try to confuse me: say yes or no."
        );

        String programPerceptionAnswer;
        if (anyMatch(answer, positiveResponses)) {
            programPerceptionAnswer = "You answered: Yes";
        } else if (anyMatch(answer, negativeResponses)) {
            programPerceptionAnswer = "You answered: No";
        } else {
            Random random = new Random();
            programPerceptionAnswer = misperceptionResponses.get(
                    random.nextInt(misperceptionResponses.size())
            );
            throw new Exception(programPerceptionAnswer);
        }

        return programPerceptionAnswer;
    }

    private static boolean anyMatch(String userAnswer, List<String> responses) {
        return responses.stream()
                .anyMatch(item -> item != null && userAnswer.trim().matches("(?i)" + item + "(!|\\.)?"));
    }

    public static String tellGoodbye() {
        List<String> goodbyes = List.of(
                "Goodbye", "Bye", "See you next time", "Later"
        );
        Random random = new Random();
        return goodbyes.get(random.nextInt(goodbyes.size()));
    }
}