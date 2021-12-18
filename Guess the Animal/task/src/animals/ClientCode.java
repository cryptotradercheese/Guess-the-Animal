package animals;

import java.util.Scanner;

public class ClientCode {
    private static Scanner scanner = new Scanner(System.in);
    private static Formatter.NameFormatter firstAnimal;
    private static Formatter.NameFormatter secondAnimal;
    private static  Formatter.CharacteristicFormatter animalCharacteristic;
    private static boolean characteristicCorrectnessForSecondAnimal;
    private static BinaryTree binaryTree = new BinaryTree();
    private static BinaryTree.Node currentNode;

    public static void greet() {
        System.out.println(RandomAnswers.greet());
    }

    public static void tellGoodbye() {
        System.out.println(RandomAnswers.tellGoodbye());
    }

    public static void enterAnimals() {
        System.out.println("I want to learn about animals.");
        System.out.println("Which animal do you like most?");

        firstAnimal = Formatter.formatName(scanner.nextLine());
        binaryTree.setRoot(new BinaryTree.Node(firstAnimal.getArticleAndAnimalName()));

        System.out.println("Wonderful! I've learned so much about animals!");
        System.out.println("Let's play a game!");
    }

    public static void suggestToPlay() {
        System.out.println("You think of an animal, and I guess it.");
        System.out.println("Press enter when you're ready.");
        scanner.nextLine();
    }

    public static void askQuestions() {
        currentNode = binaryTree.getRoot();
        BinaryTree.Node left = currentNode.getLeft();
        BinaryTree.Node right = currentNode.getRight();

        while (left != null && right != null) {
            System.out.println(currentNode.getValue());

            try {
                if (Analyzer.analyzeAnswer(scanner.nextLine())) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode = currentNode.getLeft();
                }

                left = currentNode.getLeft();
                right = currentNode.getRight();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // Animal from binary tree is always first
        firstAnimal = Formatter.formatName(currentNode.getValue());
        System.out.println("Is it " + firstAnimal.getArticleAndAnimalName()  + "?");
    }

    public static boolean isGuessCorrect() {
        while (true) {
            try {
                if (Analyzer.analyzeAnswer(scanner.nextLine())) {
                    return true;
                } else {
                    System.out.println("I give up. What animal do you have in mind?");
                    secondAnimal = Formatter.formatName(scanner.nextLine());
                    return false;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void specifyAnimalFact() {
        while (true) {
            System.out.println(
                    "Specify a fact that distinguishes " +
                    firstAnimal.getArticleAndAnimalName() +
                    " from " +
                    secondAnimal.getArticleAndAnimalName() + "."
            );
            System.out.println("The sentence should satisfy one of the following templates:");
            System.out.println("- It can ...");
            System.out.println("- It has ...");
            System.out.println("- It is a/an ...");
            System.out.println();

            String animalCharacteristicInput = scanner.nextLine();
            if (animalCharacteristicInput.matches("(?i)It (can|has|is)\\s.+")) {
                animalCharacteristic = Formatter
                        .formatCharacteristic(animalCharacteristicInput);
                break;
            }
        }
    }

    public static void checkCorrectnessForSecondAnimal() {
        System.out.println(
                "Is it correct for " + secondAnimal.getArticleAndAnimalName() + "?"
        );

        while (true) {
            String userAnswer = scanner.nextLine();

            try {
                characteristicCorrectnessForSecondAnimal = Analyzer.analyzeAnswer(userAnswer);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void declareLearnedFacts() {
        String firstStatement;
        String secondStatement;

        String question = Formatter.toUpperCaseFirst(animalCharacteristic.getQuestionVerbWithNoun("it")) +
                " " + animalCharacteristic.getCharacteristic() + "?";

        currentNode.setValue(question);

        if (characteristicCorrectnessForSecondAnimal) {
            currentNode.setLeft(new BinaryTree.Node(firstAnimal.getArticleAndAnimalName()));
            currentNode.setRight(new BinaryTree.Node(secondAnimal.getArticleAndAnimalName()));

            firstStatement = "- The " + firstAnimal.getAnimalName() + " " +
                animalCharacteristic.getNegativeVerb() + " " +
                animalCharacteristic.getCharacteristic() + ".";
            secondStatement = "- The " + secondAnimal.getAnimalName() + " " +
                    animalCharacteristic.getVerb() + " " +
                    animalCharacteristic.getCharacteristic() + ".";
        } else {
            currentNode.setLeft(new BinaryTree.Node(secondAnimal.getArticleAndAnimalName()));
            currentNode.setRight(new BinaryTree.Node(firstAnimal.getArticleAndAnimalName()));

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
        String question = Formatter
                .toUpperCaseFirst(animalCharacteristic.getQuestionVerbWithNoun("it")) +
                " " + animalCharacteristic.getCharacteristic() + "?";
        System.out.println("- " + question);
        System.out.println("Nice! I've learned so much about animals!");
    }

    public static boolean suggestPlayAgain() {
        System.out.println("Would you like to play again?");

        while (true) {
            String userAnswer = scanner.nextLine();

            try {
                return Analyzer.analyzeAnswer(userAnswer);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
