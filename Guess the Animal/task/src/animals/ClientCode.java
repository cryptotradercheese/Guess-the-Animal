package animals;

import java.util.Scanner;

public class ClientCode {
    private static Scanner scanner = new Scanner(System.in);
    private static Formatter.NameFormatter firstAnimal;
    private static Formatter.NameFormatter secondAnimal;
    private static  Formatter.CharacteristicFormatter animalCharacteristic;
    private static boolean animalCharacteristicCorrectness;

    public static void greet() {
        System.out.println(RandomAnswers.greet());
    }

    public static void tellGoodbye() {
        System.out.println(RandomAnswers.tellGoodbye());
    }

    public static void enterAnimals() {
        System.out.println("Enter the first animal:");
        firstAnimal = Formatter.formatName(scanner.nextLine());
        System.out.println("Enter the second animal:");
        secondAnimal = Formatter.formatName(scanner.nextLine());
    }

    public static void specifyAnimalFact() {
        while (true) {
            System.out.println(
                    "Specify a fact that distinguishes " +
                            firstAnimal.getArticleAndAnimalName() +
                            " from " +
                            secondAnimal.getArticleAndAnimalName() + "."
            );
            System.out.println("The sentence should be of the format: 'It can/has/is ...'.");
            System.out.println();

            String animalCharacteristicInput = scanner.nextLine();
            if (animalCharacteristicInput.matches("(?i)It (can|has|is)\\s.+")) {
                animalCharacteristic = Formatter
                        .formatCharacteristic(animalCharacteristicInput);
                break;
            } else {
                System.out.println(giveExample());
            }
        }
    }

    private static String giveExample() {
        return "The examples of a statement:" + System.lineSeparator() +
                " - It can fly" + System.lineSeparator() +
                " - It has horn" + System.lineSeparator() +
                " - It is a mammal";
    }

    public static void checkEvaluationCorrectness() {
        System.out.println(
                "Is it correct for " + secondAnimal.getArticleAndAnimalName() + "?"
        );

        while (true) {
            String userAnswer = scanner.nextLine();

            try {
                animalCharacteristicCorrectness = Analyzer.analyzeAnswer(userAnswer);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void declareLearnedFacts() {
        String firstStatement;
        String secondStatement;
        if (animalCharacteristicCorrectness) {
            firstStatement = "- The " + firstAnimal.getAnimalName() + " " +
                    animalCharacteristic.getNegativeVerb() + " " +
                    animalCharacteristic.getCharacteristic() + ".";
            secondStatement = "- The " + secondAnimal.getAnimalName() + " " +
                    animalCharacteristic.getVerb() + " " +
                    animalCharacteristic.getCharacteristic() + ".";
        } else {
            firstStatement = "- The " + firstAnimal.getAnimalName() + " " +
                    animalCharacteristic.getVerb() + " " +
                    animalCharacteristic.getCharacteristic() + ".";
            secondStatement = "- The " + secondAnimal.getAnimalName() + " " +
                    animalCharacteristic.getNegativeVerb() + " " +
                    animalCharacteristic.getCharacteristic() + ".";
        }

        System.out.println("I have learned the following facts about animals:");

        System.out.println(firstStatement);
        System.out.println(secondStatement);
    }

    public static void printDistinguishingQuestion() {
        System.out.println("I can distinguish these animals by asking the question:");
        System.out.println(
                "- " +
                Formatter.toUpperCaseFirst(animalCharacteristic.getQuestionVerbWithNoun("it")) +
                " " + animalCharacteristic.getCharacteristic() + "?"
        );
    }
}
