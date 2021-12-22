package animals;

public class Main {
    public static void main(String[] args) {
        ClientCode.parseMainArgs(args);
        ClientCode.greet();
        System.out.println();
        if (!ClientCode.doesDbExists()) {
            ClientCode.enterAnimals();
        } else {
            ClientCode.loadDb();
        }

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