package Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Logic {

    public static int numberMistakes = 3;

    public void gameLogic(Scanner scanner, String randomWord) {

        boolean hasStars = true;

        Character[] workingWord = initialWording(randomWord);
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

    public Character[] initialWording(String randomWord) {    // initialize the word for helping the user
        Character[] arrayOfStars = createArrayOfStars(randomWord.length());

        Character[] arrayOfWord = parseWordToArray(randomWord);

        Random random = new Random();
        int randomIndex = random.nextInt(randomWord.length());
        char letter1 = randomWord.charAt(randomIndex);
        char letter2 = randomWord.charAt(randomIndex);


        while (letter1 == letter2) {
            randomIndex = random.nextInt(randomWord.length());
            letter2 = randomWord.charAt(randomIndex);
        }
        for (int i = 0; i < arrayOfWord.length; i++) {
            if (letter1 == arrayOfWord[i] || letter2 == arrayOfWord[i]) {
                arrayOfStars[i] = arrayOfWord[i];
            }
        }
        displayResult(arrayOfStars);
        return arrayOfStars;
    }

    public Character[] createArrayOfStars(int length) {     // this array will be displayed

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


