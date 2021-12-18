package animals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formatter {
    public static class NameFormatter {
        private String article;
        private String animalName;
        private String articleAndAnimalName;

        private NameFormatter(String article, String animalName, String articleAndAnimalName) {
            this.article = article;
            this.animalName = animalName;
            this.articleAndAnimalName = articleAndAnimalName;
        }

        public String getArticle() {
            return article;
        }

        public String getAnimalName() {
            return animalName;
        }

        public String getArticleAndAnimalName() {
            return articleAndAnimalName;
        }
    }

    public static class CharacteristicFormatter {
        private String verb;
        private String characteristic;

        public CharacteristicFormatter(String verb, String characteristic) {
            this.verb = verb;
            this.characteristic = characteristic;
        }

        public String getVerb() {
            return verb;
        }

        public String getNegativeVerb() {
            if ("can".equals(verb)) {
                return verb + "'t";
            } else if ("has".equals(verb)) {
                return "doesn't have";
            } else if ("is".equals(verb)) {
                return verb + "n't";
            } else {
                throw new IllegalStateException("Can't identify verb field");
            }
        }

        public String getQuestionVerbWithNoun(String noun) {
            if (noun == null) {
                throw new IllegalArgumentException("Noun can't be null");
            }
            noun = noun.toLowerCase();

            if ("can".equals(verb)) {
                return verb + " " + noun;
            } else if ("has".equals(verb)) {
                return "does" + " " + noun + " have";
            } else if ("is".equals(verb)) {
                return verb + " " + noun;
            } else {
                throw new IllegalStateException("Can't identify verb field");
            }
        }

        public String getCharacteristic() {
            return characteristic;
        }
    }

    public static CharacteristicFormatter formatCharacteristic(String characteristic) {
        if (characteristic == null) {
            throw new IllegalArgumentException("Characteristic can't be null");
        }

        characteristic = characteristic.toLowerCase();;

        Matcher matcher = Pattern
                .compile("(?i)^It (?<verb>can|has|is)\\s+(?<userWords>.+)(?<![.!?]+)")
                .matcher(characteristic);
        if (matcher.find()) {
            String verb = matcher.group("verb");
            String userWords = matcher.group("userWords");
            return new CharacteristicFormatter(verb, userWords);
        } else {
            throw new IllegalArgumentException("Wrong characteristic format");
        }
    }

    public static NameFormatter formatName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name can't be null");
        }

        name = name.toLowerCase();
        String animalNamePattern = "[a-z]+(\\s[a-z]+)*";

        String article;
        String animalName;
        String articleAndAnimalName;

        Matcher matcher = Pattern
                .compile("(an?|the)\\s(" + animalNamePattern + ")")
                .matcher(name);

        if (matcher.find()) {
            article = matcher.group(1);
            animalName = matcher.group(2);
        } else if (name.matches(animalNamePattern)) {
            article = "";
            animalName = name;
        } else {
            throw new IllegalArgumentException("Wrong name format");
        }

        if ("the".equals(article) || "".equals(article)) {
            article = determineIndefiniteArticle(animalName);
        }

        articleAndAnimalName = article + " " + animalName;

        return new NameFormatter(article, animalName, articleAndAnimalName);
    }

    public static String toUpperCaseFirst(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Argument can't be null");
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private static String determineIndefiniteArticle(String animalName) {
        return animalName.matches("(?i)[aeiou].*") ? "an" : "a";
    }
}