package Service;

import Database.WordsDAO;
import Entity.Words;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Service {
    private Words word;
    private WordsDAO wordsDAO;
    private Scanner s;
    private boolean isRunning;
    Logic logic;

    public Service() {

        wordsDAO = new WordsDAO();
        s = new Scanner(System.in);
        isRunning = true;
        logic = new Logic();
    }

    public void action() {
        while (isRunning) {
            word = new Words();
            displayMenu();
            System.out.print("Please enter a command: ");
            int command = s.nextInt();
            executeCommand(command);
        }

    }

    public void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1.Insert a word.");
        System.out.println("2.View all words");
        System.out.println("3.Delete word by name.");
        System.out.println("4.Update word by id.");
        System.out.println("5.Play the game.");
        System.out.println("6.Exit.");
    }


    public void executeCommand(int command) {
        switch (command) {
            case 1:
                System.out.println("This command will insert a word");
                insertAWord();
                System.out.println("The word has been inserted.");
                break;
            case 2:
                System.out.println("This command will show you all the words from the database");
                viewAllWords();
                System.out.println("The words are listed above :)");
                break;
            case 3:
                System.out.println("This command will delete a word by id:");
                deleteWordById();
                break;
            case 4:
                System.out.println("This command will update a word by id.");
                updateWord();
                break;
            case 5:
                System.out.println("Play!");
                playTheGame(s);
                break;
            case 6:
                System.out.println("Bye!");
                isRunning = false;
            default:
        }
    }

    public void playTheGame(Scanner scanner) {

        boolean keepGoing = true;
        while (keepGoing) {
            logic.gameLogic(s, getRandomWord(wordsDAO.viewAllWords()));
            System.out.print("\nAgain? (y/n)");
            String decision = scanner.next();
            if (!decision.equalsIgnoreCase("y")) {
                keepGoing = false;
            }
        }


    }

    public void insertAWord() {
        boolean isRunning = true;

        while (isRunning) {
//            wordsDAO = new WordsDAO();
            word = new Words();
            System.out.println("Please enter the name of the word you want to insert:");
            String wordInserted = s.next();
            word.setName(wordInserted);
            wordsDAO.insertWord(word);
            System.out.println("Insert another? (y/n)");
            String response = s.next();
            if(!response.equalsIgnoreCase("y")){
                isRunning = false;
            }
        }
    }

    public void viewAllWords() {

        System.out.println(wordsDAO.viewAllWords());
    }

    public void deleteWordById() {
        System.out.print("Enter the id of the word you want to delete: ");
        int deleteWord = s.nextInt();
        List<Words> allWords = wordsDAO.viewAllWords();
        for (Words w : allWords) {
            if (w.getId() == deleteWord) {
                wordsDAO.deleteWordsById(w);
                break;
            }
        }
    }

    public void updateWord() {
        Words newWord = new Words();
        System.out.println("Insert the id of the word you want to update: ");
        int updatedId = s.nextInt();
        newWord.setId(updatedId);

        System.out.println("Insert the name of the updated word: ");
        String updatedWord = s.next();
        newWord.setName(updatedWord);

        wordsDAO.updateWordById(newWord);
    }

    private String getRandomWord(List<Words> listOfWords) {

        int randomIndex = new Random().nextInt(listOfWords.size()); // first we generate a random number
        return listOfWords.get(randomIndex).getName(); // the random number is the index for
                                                        // a position from the list
    }
}
