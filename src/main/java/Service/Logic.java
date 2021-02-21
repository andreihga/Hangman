package Service;

import Database.WordsDAO;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Logic {
    WordsDAO wordsDAO = new WordsDAO();
    Service service = new Service();

    public String randomWord = service.getRandomWord(wordsDAO.viewAllWords());
    public static int numberMistakes = 3;


    public void gameLogic(Scanner scanner, String randomWord, Random random) {

        boolean hasStars = true;

        Character[] workingWord = difficulty(scanner, random);
        Character[] originalWord = parseWordToArray(randomWord);
        while (hasStars) {
            Character letter = insertLetter(scanner);
            validateOption(letter, originalWord, workingWord);

            displayResult(workingWord);

            List<Character> characterList = Arrays.asList(workingWord);

            if (!characterList.contains('_')) {
                hasStars = false;
                System.out.println("\nWell done! The word is indeed " + randomWord + ".");
            }

            if (Logic.numberMistakes == 0) {
                System.out.println("\nYou're out!");
                hasStars = false;
            }
        }
        Logic.numberMistakes = 3;
    }

    public Character[] difficulty(Scanner scanner, Random random) {
        Character[] workingWordArray = new Character[randomWord.length()];
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Choose difficulty: ");
            System.out.println("1. Easy\n2. Medium\n3. Hard");
            int difficultyChoice = scanner.nextInt();

            switch (difficultyChoice) {
                case 1:
                    workingWordArray = difficultyEasy( random, scanner);
                    isRunning = false;
                    break;
                case 2:
                    workingWordArray = difficultyMedium(randomWord);
                    isRunning = false;
                    break;
                case 3:
                    workingWordArray = difficultyHard(randomWord);
                    isRunning = false;
                    break;
                default:
                    System.out.println("Not a valid option. Valid options 1,2 or 3");
            }
        }
        return workingWordArray;
    }

    public Character[] difficultyEasy(Random random, Scanner scanner) {    // initialize the word for helping the user

        while (randomWord.length() > 6) {
            randomWord = service.getRandomWord(wordsDAO.viewAllWords());
        }

        Character[] arrayOfUnderscores = createArrayOfUnderscores(randomWord.length());

        Character[] arrayOfWord = parseWordToArray(randomWord);


        int randomIndex = random.nextInt(randomWord.length());
        char letter1 = randomWord.charAt(randomIndex);
        char letter2 = randomWord.charAt(randomIndex);


        while (letter1 == letter2) {
            randomIndex = random.nextInt(randomWord.length());
            letter2 = randomWord.charAt(randomIndex);
        }
        for (int i = 0; i < arrayOfWord.length; i++) {
            if (letter1 == arrayOfWord[i] || letter2 == arrayOfWord[i]) {
                arrayOfUnderscores[i] = arrayOfWord[i];
            }
        }
        displayResult(arrayOfUnderscores);
        return arrayOfUnderscores;
    }

    public Character[] difficultyMedium(String randomWord) {
        Character[] arrayOfUnderscores = createArrayOfUnderscores(randomWord.length());
        Character[] arrayOfWord = parseWordToArray(randomWord);
        System.out.println("Difficulty Medium Test");
        return arrayOfUnderscores;
    }

    public Character[] difficultyHard(String randomWord) {
        Character[] arrayOfUnderscores = createArrayOfUnderscores(randomWord.length());
        Character[] arrayOfWord = parseWordToArray(randomWord);
        System.out.println("Difficulty Hard Test");
        return arrayOfUnderscores;
    }

    public void displayResult(Character[] resultArray) {
        for (int i = 0; i < resultArray.length; i++) {
            System.out.print(resultArray[i]);
        }
    }

    public void validateOption(Character letter, Character[] originalWord, Character[] workingWord) {
        int validOption = 0;
        for (int i = 0; i < originalWord.length; i++) {
            if (letter == originalWord[i]) {
                workingWord[i] = letter;
                validOption = 1;
            }
        }
        if (validOption == 0) {
            System.out.println("You have " + --Logic.numberMistakes + " tries remaining.");
        }
    }


    public Character insertLetter(Scanner scanner) {    //get a letter from the keyboard
        System.out.print("\nInsert a letter: ");
        String letter = scanner.next();
        while (letter.length() != 1) {
            System.out.println("It should be just a letter! \n");
            System.out.print("Try again:");
            letter = scanner.next();
        }
        return letter.charAt(0);
    }

    public Character[] createArrayOfUnderscores(int length) {     // this array will be displayed

        Character[] arrayOfStars = new Character[length];

        for (int i = 0; i < length; i++) {
            arrayOfStars[i] = '_';
        }
        return arrayOfStars;
    }

    public Character[] parseWordToArray(String word) {      // this array will be the word that we will compare the letters given with
        Character[] arrayForWord = new Character[word.length()];
        for (int i = 0; i < word.length(); i++) {
            arrayForWord[i] = word.charAt(i);
        }
        return arrayForWord;
    }
}


