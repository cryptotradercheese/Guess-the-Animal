package animals;

public class Main {
    public static void main(String[] args) {
        ClientCode.greet();
        System.out.println();
        ClientCode.enterAnimals();

        do {
            ClientCode.suggestToPlay();
            ClientCode.askQuestions();
            if (!ClientCode.isGuessCorrect()) {
                ClientCode.specifyAnimalFact();
                ClientCode.checkCorrectnessForSecondAnimal();
                ClientCode.declareLearnedFacts();
                ClientCode.printDistinguishingQuestion();
            }

            System.out.println();
        } while (ClientCode.suggestPlayAgain());

        ClientCode.tellGoodbye();
    }
}